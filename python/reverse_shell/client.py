from socket import socket, gethostname


def client_connection_script():
    host = gethostname()
    port = 8080

    client = socket()
    client.connect((host, port))

    message = input(" -> ")

    nick = str(input("Enter your nickname here: "))

    while message.lower().strip() != "/bye":
        client.send(message.encode())
        data = client.recv(1024).decode()

        print("Received from server: " + data)
        message = input(f"{nick} -> ")

    client.close()


if __name__ == "__main__":
    client_connection_script()
