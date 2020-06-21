import hashlib
import logging
import os
import socket
import time

import pymongo

MONGO_HOST = os.getenv('PREPARE_DB_MONGO_HOST', 'localhost')
MONGO_PORT = os.getenv('PREPARE_DB_MONGO_PORT', '27017')
MONGO_DATABASE = os.getenv('PREPARE_DB_MONGO_DATABASE', 'ms-proposal')
ADMIN_PASSWORD = 'admin'
ADMIN_HASH_PASSWORD = hashlib.md5(ADMIN_PASSWORD.encode("UTF-8")).hexdigest()
TIMEOUT = 30
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s', datefmt='%d-%b-%y %H:%M:%S')


def _check_connection():
    sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    return sock.connect_ex((MONGO_HOST, int(MONGO_PORT)))


def wait_for_services():
    i = 0
    while i < TIMEOUT:
        if _check_connection() == 0:
            logging.info('MongoDB is now ready')
            return
        time.sleep(1)
        i = i + 1
        logging.warning('{}th Try failed'.format(i))
    raise Exception('Timeout to create connection to app. Is MongoDB ready?')


def insert_data():
    client = pymongo.MongoClient("mongodb://{}:{}".format(MONGO_HOST, MONGO_PORT))
    db = client.get_database(MONGO_DATABASE)
    users_col = db.get_collection('users')
    users_count = users_col.count()
    db.drop_collection('roles')
    roles_col = db.get_collection('roles')

    if users_count == 0:
        user_doc = {
            'username': 'admin',
            'password': ADMIN_HASH_PASSWORD,
            'role': 'ADMIN'
        }

        users_col.insert(user_doc)

    roles_docs = [{
        'name': 'ADMIN',
        'permissions': [
            'ADD_USER',
        ]
    }, {
        'name': 'STUDENT',
        'permissions': [
            'ADD_PROPOSAL',
        ]
    },
    ]

    roles_col.insert(roles_docs)
    logging.info('Admin and roles added to db')


def main():
    wait_for_services()
    insert_data()


main()
