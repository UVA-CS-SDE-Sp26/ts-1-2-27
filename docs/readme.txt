TopSecret documentation starter file

data folder and ciphers folder are located in the /src/main/java folder


Basic flow of project
    if topsecret ran with no arguments:
        filehandler returns an array of filenames in the data folder, which is in the root project directory
        programcontrol/userinterface takes array and prints readable format with numbering
    if topsecret ran with one argument (file number):
        cipher assumes default key
        programcontrol requests file contents from filehandler
        if file exists:
            filehandler locates file and sends to cipher to decipher
            cipher uses key to decipher file, sends to programcontrol
                if cannot decipher, return error to programcontrol to "exit gracefully"
            programcontrol/userinterface prints contents of the file to the screen
        if file does not exist:
            filehandler lets programcontrol know
            userinterface prints error
    if topsecret ran with 2 arguments (file number) (key)
        exact same as one argument, except cipher uses argument key instead of key.txt
    if topsecret ran with 3+ arguments
        either run as if 2 or print error message


