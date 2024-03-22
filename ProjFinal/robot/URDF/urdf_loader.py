import urdfpy
from collections.abc import Mapping

# Caminho para o arquivo URDF
urdf_file = "C:\\Users\\debor\\Documents\\github\\IARTES\\ProjFinal\\robot\\URDF\\marvin.urdf"


# Carregar o modelo URDF
robot = urdfpy.URDF.load(urdf_file)

# Exibir algumas informações sobre o modelo URDF
print("Juntas:")
for joint in robot.joints:
    print(joint.name)

print("\nLinks:")
for link in robot.links:
    print(link.name)
