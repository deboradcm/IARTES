from setuptools import find_packages, setup

package_name = 'mqtt_publish'

setup(
    name=package_name,
    version='0.0.0',
    packages=find_packages(exclude=['test']),
    data_files=[
        ('share/ament_index/resource_index/packages',
            ['resource/' + package_name]),
        ('share/' + package_name, ['package.xml']),
    ],
    install_requires=['setuptools'],
    zip_safe=True,
    maintainer='debora',
    maintainer_email='debora.medeiros@icomp.ufam.edu.br',
    description='TODO: Package description',
    license='TODO: License declaration',
    tests_require=['pytest'],
    entry_points={
        'console_scripts': [
            'mqtt_publish = mqtt_publish.mqtt_pub:main'
        ],
    },
)
