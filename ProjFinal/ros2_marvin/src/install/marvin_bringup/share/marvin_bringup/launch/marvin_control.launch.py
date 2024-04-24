from launch import LaunchDescription
from launch_ros.actions import Node


def generate_launch_description():
    ld = LaunchDescription()
    
    mqtt_publisher_node = Node(
        package="mqtt_publish",
        executable="mqtt_publish"
    )
    
    topic_terminal_node = Node(
        package="topic_to_terminal",
        executable="terminal_sub"
    )
    
    
    ld.add_action(mqtt_publisher_node)
    ld.add_action(topic_terminal_node)
    return ld