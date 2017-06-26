# servicio-rest-inge2

Trabajo de facultad - JAVA

Servicios agenda.

#-1 Validar usuario POST

http://localhost:8080/WebApplication3/webresources/usuario/validarUsuario


Entrada: 

		{
			"email":"sergio.amarilla.cinco@gmail.com"
		}

Salida: 

		{
		    "valido": true,
		    "idUsuario": "2",
		    "nombre": "Clara Figueredo",
		    "email": "clamalu_7@hotmail.com"
		}





#-2 Obtener hijos POST

http://localhost:8080/WebApplication3/webresources/hijo/obtenerHijosPost

Entrada: 
		{
			"idPadre":"1"
		}

Salida: 

		[
		    {
		        "id": 3,
		        "id_padre": 4,
		        "nombre": "Sergio Amarilla",
		        "sexo": "M",
		        "edad": "15 seman"
		    },
		    {
		        "id": 5,
		        "id_padre": 4,
		        "nombre": "Juan Perez",
		        "sexo": "M",
		        "edad": "14 meses"
		    },
		    {
		        "id": 4,
		        "id_padre": 4,
		        "nombre": "Maria Perez",
		        "sexo": "F",
		        "edad": "11 meses"
		    },
		    {
		        "id": 6,
		        "id_padre": 4,
		        "nombre": "Lorena Gonzalez",
		        "sexo": "M",
		        "edad": "1 mes"
		    }
		]



#-3 Obtener vacunas por hijo POST

http://localhost:8080/WebApplication3/webresources/vacunas/obtenerVacunasPost

Entrada:

		{
			"idHijo":"2",
			"order":"id"
		}


Salida:

		[
		    {
		        "id": 1,
		        "id_hijo": 2,
		        "nombre": "Rotavirus",
		        "fecha_aplicacion": "2017-05-23",
		        "aplicada": "Si"
		    },
		    {
		        "id": 2,
		        "id_hijo": 2,
		        "nombre": "Tetanos",
		        "fecha_aplicacion": "2017-05-23",
		        "aplicada": "Si"
		    },
		    {
		        "id": 5,
		        "id_hijo": 2,
		        "nombre": "Sarampion",
		        "fecha_aplicacion": "2017-04-15",
		        "aplicada": "No"
		    },
		    {
		        "id": 6,
		        "id_hijo": 2,
		        "nombre": "Rubeola",
		        "fecha_aplicacion": "2017-05-12",
		        "aplicada": "No"
		    },
		    {
		        "id": 7,
		        "id_hijo": 2,
		        "nombre": "Influenza",
		        "fecha_aplicacion": "2017-03-15",
		        "aplicada": "Si"
		    }
		]

