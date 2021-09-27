
class FieldData {
  String key;
  String value;

  FieldData(this.key, this.value);

  factory FieldData.fromJson(dynamic json) {
    return FieldData(json['key'] as String, json['value'] as String);
  }

  @override
  String toString() {
    return '{ ${this.key}, ${this.value} }';
  }
}