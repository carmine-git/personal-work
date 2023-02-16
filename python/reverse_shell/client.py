from socket import socket, gethostname


def client_connection_script():
    host = gethostname()
    port = 5000

    client = socket()

    with socket() as client:
        client.connect((host, port))

        message = input(" -> ")

        while message.lower().strip() != "bye":
            client.send(message.encode())
            data = client.recv(1024).decode()

            print("Received from server: " + data)

            message = input(" -> ")


if __name__ == "__main__":
    client_connection_script()
