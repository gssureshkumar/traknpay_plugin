import 'dart:convert';

import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:json_to_form/json_schema.dart';
import 'package:traknplugin/traknplugin.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String form = json.encode({
    'fields': [
      {
        'key': 'country',
        'label': 'Country',
        'value': 'IND',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'amount',
        'label': 'Amount',
        'value': '2',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'city',
        'label': 'City',
        'value': 'Chennai',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'description',
        'label': 'Description',
        'value': 'Test',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'zip_code',
        'label': 'Zip code',
        'value': '630501',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'mode',
        'label': 'Mode',
        'value': 'LIVE',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'api_key',
        'label': 'API Key',
        'value': '08490a7f-3ffc-44d5-b14e-c7509d6cc7b2',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'phone',
        'label': 'Phone',
        'value': '9597403366',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'name',
        'label': 'Name',
        'value': 'Senthil',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'address_line_1',
        'label': 'Address Line1',
        'value': 'ad1',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'address_line_2',
        'label': 'Address Line2',
        'value': 'ad2',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'order_id',
        'label': 'Order Id',
        'value': 'TEST4000',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'return_url',
        'label': 'Return Url',
        'value': 'http://localhost:8888/paymentresponse',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'currency',
        'label': 'Currency',
        'value': 'INR',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'state',
        'label': 'State',
        'value': 'Tamilnadu',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'email',
        'label': 'Email',
        'value': 'emailsenthil@test.com',
        'type': 'Input',
        'required': true
      },
      {
        'key': 'hash',
        'label': 'Hash',
        'value': 'E5EA941CFBFD378F0F8F82EA650D66675C641D4D90531FA7A8EF4622BCCDCCF13403CC876960513B35684D71418D4512CFEF230A7BA8044173B949E8D0C5645F',
        'type': 'Input',
        'required': true
      }
    ]
  });
  dynamic response;
  Map decorations = {
    'country': InputDecoration(
      border: OutlineInputBorder(),
    ),
    'amount': InputDecoration(border: OutlineInputBorder()),
    'city': InputDecoration(border: OutlineInputBorder()),
    'description': InputDecoration(border: OutlineInputBorder()),
    'zip_code': InputDecoration(border: OutlineInputBorder()),
    'mode': InputDecoration(border: OutlineInputBorder()),
    'api_key': InputDecoration(border: OutlineInputBorder()),
    'phone': InputDecoration(border: OutlineInputBorder()),
    'name': InputDecoration(border: OutlineInputBorder()),
    'address_line_1': InputDecoration(border: OutlineInputBorder()),
    'return_url': InputDecoration(border: OutlineInputBorder()),
    'currency': InputDecoration(border: OutlineInputBorder()),
    'state': InputDecoration(border: OutlineInputBorder()),
    'address_line_2': InputDecoration(border: OutlineInputBorder()),
    'order_id': InputDecoration(border: OutlineInputBorder()),
    'email': InputDecoration(border: OutlineInputBorder()),
    'hash': InputDecoration(border: OutlineInputBorder()),
  };

  @override
  void initState() {
    super.initState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPay(String request) async {
    String amount;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      print(request);
      amount = await Traknplugin.initPay(request);
    } on PlatformException {
      amount = 'Failed to get initiate.';
    }

    if (!mounted) return;
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Trakn Pay'),
        ),
        body: Padding(
            padding: EdgeInsets.all(10),
            child: ListView(
              children: <Widget>[
                Container(
                    alignment: Alignment.center,
                    padding: EdgeInsets.all(10),
                    child: Text(
                      'Welcome Payment Gateway',
                      style: TextStyle(
                          color: Colors.blue,
                          fontWeight: FontWeight.w500,
                          fontSize: 30),
                    )),
                new JsonSchema(
                  decorations: decorations,
                  form: form,
                  onChanged: (dynamic response) {
                    this.response = response;
                  },
                  actionSave: (data) {
                    List<dynamic> fields = data["fields"];
                    Map requestMap = Map<String, String>();
                    for (var i = 0; i < fields.length; i++) {
                      requestMap[fields[i]['key']] = fields[i]['value'];
                    }
                    initPay(json.encode(requestMap));
                  },
                  buttonSave: new Container(
                    height: 40.0,
                    color: Colors.blueAccent,
                    child: Center(
                      child: Text("Pay",
                          style: TextStyle(
                              color: Colors.white,
                              fontWeight: FontWeight.bold)),
                    ),
                  ),
                ),
                Container(
                    alignment: Alignment.center,
                    padding: EdgeInsets.all(10),
                    child: Text(
                      'Paid Amount : ',
                      style: TextStyle(
                          color: Colors.blue,
                          fontWeight: FontWeight.w500,
                          fontSize: 20),
                    ))
              ],
            )),
      ),
    );
  }
}
