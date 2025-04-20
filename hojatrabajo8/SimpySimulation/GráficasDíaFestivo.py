#Para facilitar la elaboración de gráficas se supone que en un día festivo llegan a la emergencia 10 personas, en un intervalo de 5
from MétodosGráficas import *
capacidades = range(1, 11)
capacidades2 = range(1, 16)
numpacientes = 100
interval = 5

recurso1 = 'Doctores'
datos1 = run_experiments(recurso1, capacidades, numpacientes, interval)

recurso2 = 'Enfermeras'
datos2 = run_experiments(recurso2, capacidades, numpacientes, interval)

recurso3 = 'RayosX'
datos3 = run_experiments(recurso3, capacidades, numpacientes, interval)

recurso4 = 'Laboratorio'
datos4 = run_experiments(recurso4, capacidades2, numpacientes, interval)

recurso5 = 'Operaciones'
datos5 = run_experiments(recurso5, capacidades, numpacientes, interval)

graficar_resultados(datos1, recurso1)
graficar_resultados(datos2, recurso2)
graficar_resultados(datos3, recurso3)
graficar_resultados(datos4, recurso4)
graficar_resultados(datos5, recurso5)
