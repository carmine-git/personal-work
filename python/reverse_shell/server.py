from socket import socket, gethostname, SO_REUSEADDR, SOL_SOCKET


def server_connection_script():
    host = gethostname()
    port = 8080

    server = socket()
    server.bind((host, port))

    server.setsockopt(SOL_SOCKET, SO_REUSEADDR, 1)

    server.listen(2)

    connection, address = server.accept()

    print(f"Connection: {str(address)}")

    nick = input("Enter your nickname here: ")

    while True:
        data = connection.recv().decode()
        if not data:
            break
        print(str(data))
        data = input(f"{nick}: ")
        format_message = f"{nick}: {data}"
        connection.send(format_message.encode())

    connection.close()


if __name__ == "__main__":
    server_connection_script()
