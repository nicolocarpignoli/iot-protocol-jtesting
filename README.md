# iot-protocol-jtesting

## Overview

A collection of Java spikes about IoT protocols. It includes AMQP, MQTT, COAP and WebSocket. It also comes with a simple parser to print results of tests in a common format.

## Motivation

It is a fast and easy way to do basic tests for most important IoT protocols; you can get results about lost packets and time between sending and receving messages. 

## Usage

Just configure with your ip address and port number and launch the main classes. After you can copy and paste your data in `rlog.txt`  file for packet receive times and `slog.txt`  for sending times. Then just run the parser class to get the calculated final results of tests.

## Contributing

Pull-requests and Issues are welcome.

## License

This project is released under MIT license as you can check in LICENSE.md file.
