# Some examples of how to use libstreaming

This repository consolidates all 3 libstreaming example into one android project.

### Libstreaming ?

You can find out more about libstreaming [here](https://github.com/fyhertz/libstreaming).

### Example 1

Shows how to use the RTSP server.

Set the startup intent as Mainactivity4

### Example 2

Shows how to start a stream and obtain a SDP that you will then want to hand to the receiver.

Set the startup intent as Mainactivity

### Example 3

**This example shows how you can use libstreaming with a Wowza Media Server.**

Set the startup intent as Mainactivity2

[Read this tutorial to find out of to use it](https://github.com/fyhertz/libstreaming/wiki/Using-libstreaming-with-Wowza-Media-Server)

### Build instructions

1. Clone the repository

2. libstreaming is referenced as git submodule in this repo, so you will need to run the two following commands:
```sh
git submodule init
git submodule update
```

3. Run the **android project update** command in the libstreaming directory and in the directory of the example you wish to compile:
```sh
cd libstreaming
android update project --path . --target android-21
cd ../example3/app
android update project --path . --target android-21
```

4. Run ant
```sh
ant debug
```

**Note: you will need to run 'ant clean' before compiling another example!**
