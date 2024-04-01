import rclpy
from rclpy.node import Node
from std_msgs.msg import String
import paho.mqtt.client as mqtt
import json

class MQTTToTopicNode(Node):
    def __init__(self):
        super().__init__('mqtt_to_topic_node')
        self.publisher = self.create_publisher(String, 'mqtt_data', 10)
        self.client = mqtt.Client()
        self.client.on_connect = self.on_connect
        self.client.on_message = self.on_message
        self.client.connect('broker.hivemq.com', 1883)
        self.client.subscribe('mqtt_topic')
        self.client.loop_forever()

    def on_connect(self, client, userdata, flags, rc):
        self.get_logger().info("Connected to MQTT broker with result code %d." % rc)

    def on_message(self, client, userdata, msg):
        try:
            data = json.loads(msg.payload.decode())
            mqtt_data = String()
            mqtt_data.data = json.dumps(data)
            self.publisher.publish(mqtt_data)
            self.get_logger().info("Received MQTT data and published to ROS topic.")
        except Exception as e:
            self.get_logger().error("Error parsing MQTT message: %s" % str(e))

def main(args=None):
    rclpy.init(args=args)
    node = MQTTToTopicNode()
    try:
        rclpy.spin(node)
    except KeyboardInterrupt:
        pass
    node.destroy_node()
    rclpy.shutdown()

if __name__ == '__main__':
    main()
