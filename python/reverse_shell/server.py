from socket import AF_INET, SOCK_STREAM, socket, gethostname


def server_connection_script():
    host = gethostname()
    port = 8080

    server = socket()
    server.bind((host, port))

    server.listen(2)

    with server.accept() as connection, server.accept() as address:
        print(f"Connection: {str(address)}")

        while True:
            data = connection.recv(1024).decode()
            if not data:
                break
            print("Connected user's data " + str(data))
            data = input("  =>  ")
            connection.send(data.encode())


if __name__ == "__main__":
    server_connection_script()
