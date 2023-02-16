from socket import socket, gethostname


def client_connection_script():
    host = gethostname()
    port = 8080

    client = socket()
    client.connect((host, port))
    nick = str(input("Enter your nickname here: "))

    print(f"Coucou {nick}")

    while True:
        message = input(f"{nick}: ")

        if message == "/bye":
            break

        if message.startswith("/nick"):
            new_nickname = message.split(" ")[1]
            nick = new_nickname

        format_messge = f"{nick}: {message}"

        client.send(format_messge.encode())
        data = client.recv(1024).decode()

        print(data)

    client.close()


if __name__ == "__main__":
    client_connection_script()
