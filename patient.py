"""patients_for_id = {}"""
# -*- coding: utf-8 -*-
from PyQt5.QtWidgets import *
from PyQt5.QtCore import *
import db

class Patient(object):
    """ This class contains the attributes and methods that can be applied on a
        patient.
    """
    def __init__(self, id, Fecha_consulta, H_Clinica, Nombre, Apellido, Carnet_de_identidad, Fecha_de_nacimiento, Estado_civil, Peso, Talla, Establecimiento_o_centro_de_salud, Provincia, Municipio, Localidad, Direccion, Numero_de_telefono_1, Numero_de_telefono_2, Croquis_vivienda):
        """ The constructor initializes the attributes of a patient with the
            values given as parameters.
        """

        self._id = id
        self._Fecha_consulta = Fecha_consulta
        self._H_Clinica = H_Clinica
        self._Carnet_de_identidad = Carnet_de_identidad
        self._Nombre = Nombre
        self._Apellido = Apellido
        self._Fecha_de_nacimiento = Fecha_de_nacimiento
        self._Estado_civil = Estado_civil
        self._Peso = Peso
        self._Talla = Talla
        self._Establecimiento_o_centro_de_salud = Establecimiento_o_centro_de_salud
        self._Provincia = Provincia
        self._Municipio = Municipio
        self._Localidad = Localidad
        self._Direccion = Direccion
        self._Numero_de_telefono_1 = Numero_de_telefono_1
        self._Numero_de_telefono_2 = Numero_de_telefono_2
        self._Croquis_vivienda = Croquis_vivienda

    """ The following methods are used to get the attributes of a patient.
    """
    def id(self):
        return self._id

    def fecha_consulta(self):
        return self._Fecha_consulta

    def h_Clinica(self):
        return self._H_Clinica

    def nombre(self):
        return self._Nombre

    def apellido(self):
        return self._Apellido
    
    def carnet_de_identidad(self):
        return self._Carnet_de_identidad

    def fecha_de_nacimiento(self):
        return self._Fecha_de_nacimiento

    def estado_civil(self):
        return self._Estado_civil

    def peso(self):
        return self._Peso

    def talla(self):
        return self._Talla

    def establecimiento_o_centro_de_salud(self):
        return self._Establecimiento_o_centro_de_salud

    def provincia(self):
        return self._Provincia

    def municipio(self):
        return self._Municipio

    def localidad(self):
        return self._Localidad

    def direccion(self):
        return self._Direccion

    def numero_de_telefono_1(self):
        return self._Numero_de_telefono_1

    def numero_de_telefono_2(self):
        return self._Numero_de_telefono_2

    def croquis_vivienda(self):
        return self._Croquis_vivienda


    """ The following methods are used to set <value> as the value of an
        attribute of a patient.
    """
    def setFecha_consulta(self, value):
        self._Fecha_consulta = value

    def setH_Clinica(self, value):
        self._H_Clinica = value

    def setNombre(self, value):
        self._Nombre = value

    def setApellido(self, value):
        self._Apellido = value
    
    def setCarnet_de_identidad(self, value):
        self.Carnet_de_identidad = value

    def setFecha_de_nacimiento(self, value):
        self._Fecha_de_nacimiento = value

    def setEstado_civil(self, value):
        self._Estado_civil = value

    def setPeso(self, value):
        self._Peso = value

    def setTalla(self, value):
        self._Talla = value

    def setEstablecimiento_o_centro_de_salud(self, value):
        self._Establecimiento_o_centro_de_salud = value

    def setProvincia(self, value):
        self._Provincia = value

    def setMunicipio(self, value):
        self._Municipio = value

    def setLocalidad(self, value):
        self._Localidad = value

    def setDireccion(self, value):
        self._Direccion = value

    def setNumero_de_telefono_1(self, value):
        self._Numero_de_telefono_1 = value

    def setNumero_de_telefono_2(self, value):
        self._Numero_de_telefono_2 = value

    def setCroquis_vivienda(self, value):
        self._Croquis_vivienda = value

    def save(self,option):
        """ Updates, in the database, the attributes of a database patient added
            or modified by the user in patientdialog. Remind that, when adding a
            patient, it is saved in the database with empty attributes by
            default.
        """
        # Ok, we have modified a Patient, now it is time to store it in the database.
        query = db.Query() #The db.Query class provides a means of executing and manipulating SQL statements. Constructs a db.Query object using the database db.
        query.prepare("UPDATE pacientes SET Fecha_consulta = ?,H_Clinica =?,Nombre =?,Apellido =?,Carnet_de_Identidad =?, Fecha_de_nacimiento =?,Estado_civil =?,Peso =?,Talla =?,Establecimiento_o_centro_de_salud =?,Provincia =?,Municipio =?,Localidad =?,Direccion =?,Numero_de_telefono_1 =?,Numero_de_telefono_2 =?,Croquis_vivienda =?, Fecha_de_insercion = CURRENT_DATE() WHERE id=?")
        query.addBindValue(self.fecha_consulta())
        query.addBindValue(self.h_Clinica())
        query.addBindValue(self.nombre())
        query.addBindValue(self.apellido())
        query.addBindValue(self.carnet_de_identidad())
        query.addBindValue(self.fecha_de_nacimiento())
        query.addBindValue(self.estado_civil())
        query.addBindValue(self.peso())
        query.addBindValue(self.talla())
        query.addBindValue(self.establecimiento_o_centro_de_salud())
        query.addBindValue(self.provincia())
        query.addBindValue(self.municipio())
        query.addBindValue(self.localidad())
        query.addBindValue(self.direccion())
        query.addBindValue(self.numero_de_telefono_1())
        query.addBindValue(self.numero_de_telefono_2())
        query.addBindValue(self.croquis_vivienda())
        query.addBindValue(self.id())
        query.exec_()

def allPatients(entry):
    """ Returns a list with all the patients from the database including <entry>
        in their name, first name or id. This parameter can be empty.
    """
    # Return the list of all the patients in the database
    query = db.Query()
    query.prepare("SELECT id,Fecha_consulta,H_Clinica,Nombre,Apellido,Carnet_de_Identidad,Fecha_de_nacimiento,Estado_civil,Peso,Talla,Establecimiento_o_centro_de_salud,Provincia,Municipio,Localidad,Direccion,Numero_de_telefono_1,Numero_de_telefono_2,Croquis_vivienda  FROM pacientes WHERE Nombre LIKE ? OR Apellido LIKE ? OR ID LIKE ?")
    query.addBindValue('%' + entry + '%')
    query.addBindValue('%' + entry + '%')
    query.addBindValue('%' + entry + '%')
    query.exec_()
    patients = []
    while query.next():
        patients.append(Patient(query.value(0),query.value(1),query.value(2),query.value(3),query.value(4),query.value(5),query.value(6),query.value(7),query.value(8),query.value(9),query.value(10),query.value(11),query.value(12),query.value(13),query.value(14),query.value(15),query.value(16), query.value(17)))
    return patients

def getPatient(id):
    """ Returns the database patient corresponding to the given <id>.
    """
    query = db.Query()
    query.prepare("SELECT id,Fecha_consulta,H_Clinica,Nombre,Apellido,Carnet_de_Identidad,Fecha_de_nacimiento,Estado_civil,Peso,Talla,Establecimiento_o_centro_de_salud,Provincia,Municipio,Localidad,Direccion,Numero_de_telefono_1,Numero_de_telefono_2,Croquis_vivienda FROM pacientes WHERE id=?")
    query.addBindValue(id)
    query.exec_()
    query.next()        # Juste après le exec_(), query est positionné avant le premier enregistrement. Il faut faire un next pour se mettre sur le premier enregistrement et pouvoir le lire.
    return Patient(query.value(0),query.value(1),query.value(2),query.value(3),query.value(4),query.value(5),query.value(6),query.value(7),query.value(8),query.value(9),query.value(10),query.value(11),query.value(12),query.value(13),query.value(14),query.value(15),query.value(16),query.value(17))

def createPatient():
    """ Returns a new database patient.
    """
    # Create an patient with no name and a new unique ID
    query = db.Query()
    query.prepare("INSERT INTO pacientes(Fecha_de_insercion) VALUES(CURRENT_DATE())")   # <-- Pas oublier d'échapper les guillemets dans les chaines de caractère
    print(query.exec_())
    return Patient(query.lastInsertId(), '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '')

def removePatient(id):
    """ Removes a database patient having <id> in the database.
    """
    # Remove an patient having the selected id
    query = db.Query()
    query.prepare("DELETE FROM pacientes WHERE id =?")
    query.addBindValue(id)      # <-- id et pas id(), car c'est bien le paramètre id de la fonction qu'on veut (et pas appeler une hypothétique fonction id()).
    query.exec_()

