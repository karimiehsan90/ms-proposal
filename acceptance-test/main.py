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


def _check_connection():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    return sock.connect_ex((APP_SERVER_HOST, int(APP_SERVER_PORT)))


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

    user_doc = {
        'username': 'admin',
        'password': ADMIN_HASH_PASSWORD,
        'role': 'ADMIN'
    }

    users_col.insert(user_doc)
    logging.info('Admin added to db')


def test_invalid_login():
    response = requests.post('http://{}:{}/auth/login'.format(APP_SERVER_HOST, APP_SERVER_PORT),
                             data={'username': 'admin', 'password': 'wrong-password'})
    content = response.content.decode('UTF-8')
    response_dict = json.loads(content)
    assert response_dict.get('success', False) is False
    logging.info('login failed')


def test_valid_login():
    response = requests.post('http://{}:{}/auth/login'.format(APP_SERVER_HOST, APP_SERVER_PORT),
                             data={'username': 'admin', 'password': ADMIN_PASSWORD})
    content = response.content.decode('UTF-8')
    response_dict = json.loads(content)
    assert response_dict.get('success', False) is True
    logging.info('login succeed')
    return response_dict


def main():
    wait_for_services()
    insert_data()
    test_invalid_login()
    action_result = test_valid_login()


main()
