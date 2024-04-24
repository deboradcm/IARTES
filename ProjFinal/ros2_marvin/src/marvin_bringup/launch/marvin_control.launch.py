import os
from launch import LaunchDescription
from launch_ros.actions import Node
from launch.actions import IncludeLaunchDescription
from launch.launch_description_sources import PythonLaunchDescriptionSource




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
    
    marvin_description_launch = IncludeLaunchDescription(
        PythonLaunchDescriptionSource([os.path.join('/home/debora/IARTES/ProjFinal/ros2_marvin/src/marvin_description/launch/display.launch.py')])
    )
  
    ld.add_action(mqtt_publisher_node)
    ld.add_action(topic_terminal_node)
    ld.add_action(marvin_description_launch)
    return ld
