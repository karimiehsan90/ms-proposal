import os
import hashlib
import pymongo
import requests
import json
import socket
import time
import logging

MONGO_HOST = os.getenv('ACCEPTANCE_TEST_MONGO_HOST', 'localhost')
MONGO_PORT = os.getenv('ACCEPTANCE_TEST_MONGO_PORT', '27017')
MONGO_DATABASE = os.getenv('ACCEPTANCE_TEST_MONGO_DATABASE', 'ms-proposal')
APP_SERVER_HOST = os.getenv('ACCEPTANCE_TEST_APP_HOST', 'localhost')
APP_SERVER_PORT = os.getenv('ACCEPTANCE_TEST_APP_PORT', '8080')
ADMIN_PASSWORD = 'admin'
ADMIN_HASH_PASSWORD = hashlib.md5(ADMIN_PASSWORD.encode("UTF-8")).hexdigest()
TIMEOUT = 30
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s', datefmt='%d-%b-%y %H:%M:%S')
FIRST_STUDENT = {
    'name': 'دانشجو',
    'username': 'student',
    'password': 'student',
    'role': 'STUDENT',
    'id_number': '0123456789',
    'field': 'نرم افزار',
}

SECOND_STUDENT = {
    'name': 'دانشجو',
    'username': 'student2',
    'password': 'student2',
    'role': 'STUDENT',
    'id_number': '0123456789',
    'field': 'نرم افزار',
}

WRONG_STUDENTS = [
    {
        'username': 'student',
        'password': 'student',
        'role': 'STUDENT',
        'id_number': '0123456789',
        'field': 'نرم افزار',
    },
    {
        'name': 'دانشجو',
        'username': None,
        'password': 'student',
        'role': 'STUDENT',
        'id_number': '0123456789',
        'field': 'نرم افزار',
    },
    {
        'name': 'دانشجو',
        'username': 'student',
        'role': 'STUDENT',
        'id_number': '0123456789',
        'field': 'نرم افزار',
    },
    {
        'name': 'دانشجو',
        'username': 'student',
        'password': 'student',
        'role': 'WRONG_ROLE',
        'id_number': '0123456789',
        'field': 'نرم افزار',
    },
    {
        'name': 'دانشجو',
        'username': 'student',
        'password': 'student',
        'role': 'STUDENT',
        'field': 'نرم افزار',
    },
    {
        'name': 'دانشجو',
        'username': 'student',
        'password': 'student',
        'role': 'STUDENT',
        'id_number': '0123456789',
        'field': 'نرم افزار',
    }
]


def _check_connection():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    return sock.connect_ex((APP_SERVER_HOST, int(APP_SERVER_PORT)))


def _get_dict_from_response(response):
    content = response.content.decode('UTF-8')
    return json.loads(content)


def wait_for_services():
    i = 0
    while i < TIMEOUT:
        if _check_connection() == 0:
            logging.info('Application is now ready')
            return
        time.sleep(1)
        i = i + 1
        logging.warning('{}th Try failed'.format(i))
    raise Exception('Timeout to create connection to app. Is web-proxy ready?')


def insert_data():
    client = pymongo.MongoClient("mongodb://{}:{}".format(MONGO_HOST, MONGO_PORT))
    db = client.get_database(MONGO_DATABASE)
    users_col = db.get_collection('users')
    roles_col = db.get_collection('roles')

    user_doc = {
        'username': 'admin',
        'password': ADMIN_HASH_PASSWORD,
        'role': 'ADMIN'
    }

    roles_docs = [{
        'name': 'ADMIN',
        'permissions': [
            'ADD_USER',
        ]
    }
    ]

    users_col.insert(user_doc)
    roles_col.insert(roles_docs)
    logging.info('Admin and roles added to db')


def test_login(username, password, expected_result):
    response = requests.post('http://{}:{}/auth/login'.format(APP_SERVER_HOST, APP_SERVER_PORT),
                             data={'username': username, 'password': password})
    response_dict = _get_dict_from_response(response)
    assert response_dict.get('success', False) is expected_result
    logging.info('login {} for user {}'.format('succeed' if expected_result else 'failed', username))
    return response_dict


def test_add_user(token, obj, expected_result):
    admin_headers = {
        'ms_proposal_token': token,
    }
    response = requests.post('http://{}:{}/user/add'.format(APP_SERVER_HOST, APP_SERVER_PORT),
                             json=obj, headers=admin_headers)
    response_dict = _get_dict_from_response(response)
    assert response_dict.get('success', False) is expected_result
    logging.info('add-user {} for user {}'.format('succeed' if expected_result else 'failed', obj['username']))


def main():
    wait_for_services()
    insert_data()
    test_login('admin', 'wrong-password', False)
    admin_login_result = test_login('admin', 'admin', True)
    admin_user = admin_login_result['data']
    test_add_user(admin_user['token'], FIRST_STUDENT, True)
    for wrong_student in WRONG_STUDENTS:
        test_add_user(admin_user['token'], wrong_student, False)
    duplicate_student = FIRST_STUDENT
    test_add_user(admin_user['token'], duplicate_student, False)
    first_student_login_result = test_login(FIRST_STUDENT['username'], FIRST_STUDENT['password'], True)
    first_student_user = first_student_login_result['data']
    test_add_user(first_student_user['token'], SECOND_STUDENT, False)
    test_add_user(admin_user['token'], SECOND_STUDENT, True)
    second_student_login_result = test_login(SECOND_STUDENT['username'], SECOND_STUDENT['password'], True)
    second_student_user = second_student_login_result['data']


main()
