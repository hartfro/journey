package journey.cli.units.notLoggedIn;

import java.time.LocalDate;
import java.util.Scanner;

import journey.cli.Input;
import journey.Main;
import journey.cli.Unit;
import journey.paciente.Paciente;
import journey.paciente.Sexo;

public class RegistrarUsuario implements Unit {
    public void display(Main app, Scanner scanner) {
        String username = "";

        while (true) {
            username = Input.leerString(scanner, "Ingrese un nombre de usuario para el paciente:\n");

            // Si el nombre de usuario ya está ocupado.
            if (app.pacientes.containsKey(username)) {
                System.out.println("Nombre de usuario ya ocupado. Intente de nuevo.");
                continue;
            }

            break;
        }

        String password = Input.leerString(scanner, "Ingrese la contraseña del paciente:\n");

        String nombre = Input.leerAlfa(scanner, "Ingrese el nombre del paciente:\n", "El nombre no puede contener caracteres no-alfabéticos.");
        String apellido = Input.leerAlfa(scanner, "Ingrese el apellido del paciente:\n", "El apellido no puede contener caracteres no-alfabéticos.");
        LocalDate fechaNacimiento = Input.leerFechaNoFutura(scanner, "Ingrese la fecha de nacimiento (dd-mm-yyyy):\n");

        int sexoInt = Input.leerEnteroEntre(scanner, "Ingrese el sexo del paciente (1. hombre, 2. mujer):\n", 1, 2);
        Sexo sexo = sexoInt == 1 ? Sexo.MASCULINO : Sexo.FEMENINO;

        float peso = Input.leerFloatPositivo(scanner, "Ingrese el peso del paciente (en kg):\n");
        int altura = Input.leerEnteroEntre(scanner, "Ingrese la altura del paciente (en cm):\n", 1, 250);

        String numeroContacto = Input.leerNumeroCelular(scanner, "Ingrese un número celular de contacto:\n");
        String ocupacion = Input.leerAlfa(scanner, "Ingrese la ocupación del paciente:\n", "La ocupación no puede contener caracteres no-alfabéticos.");

        Paciente paciente = new Paciente(username, password, nombre, apellido, fechaNacimiento, sexo, peso, altura,
                numeroContacto, ocupacion);
        app.pacientes.put(username, paciente);

        System.out.println("¡Registro exitoso!");
    }
}
