cmake_minimum_required(VERSION 3.8)
project(marvin_bringup)

if(CMAKE_COMPILER_IS_GNUCXX OR CMAKE_CXX_COMPILER_ID MATCHES "Clang")
  add_compile_options(-Wall -Wextra -Wpedantic)
endif()

# find dependencies
find_package(ament_cmake REQUIRED)


install(DIRECTORY
  launch
  DESTINATION share/${PROJECT_NAME}
)

install(
  DIRECTORY ${CMAKE_SOURCE_DIR}/../marvin_description/urdf
  DESTINATION share/${PROJECT_NAME}
)

install(DIRECTORY
  launch
  DESTINATION share/${PROJECT_NAME}
)

install(
  DIRECTORY ${CMAKE_SOURCE_DIR}/../marvin_description/urdf
  DESTINATION share/${PROJECT_NAME}
)





ament_package()
