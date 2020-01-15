package aed.hibernate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import aed.hibernate.objetos.Estancias;
import aed.hibernate.objetos.Estudiantes;
import aed.hibernate.objetos.HibernateUtil;
import aed.hibernate.objetos.Residencias;
import aed.hibernate.objetos.ResidenciasObservaciones;
import aed.hibernate.objetos.Universidades;

public class Main {

	private static int opcion = 0;
	private static Scanner scanner;
	private static Session sesion = HibernateUtil.getSessionFactory().openSession();

	public static void main(String[] args) {
		System.out.println("PABLO GARCÍA GÓMEZ");
		System.out.println();
		eleccionSentencia();
		sesion.close();
	}

	private static void eleccionSentencia() {
		System.out.println();
		System.out.println("Elija una acción(debe introducir el número):");
		System.out.println("1: Mostrar datos");
		System.out.println("2: Insertar dato");
		System.out.println("3: Modificar dato");
		System.out.println("4: Borrar Residencia");
		System.out.println("5: Desconectar y cerrar");
		scanner = new Scanner(System.in);
		opcion = scanner.nextInt();
		sentencias();
	}

	private static void sentencias() {
		switch (opcion) {
		case 1:
			System.out.println();
			System.out.println("Elija una acción(debe introducir el número):");
			System.out.println("1: Mostrar Residencia y Observaciones");
			System.out.println("2: Mostrar Estancias y sus datos");
			System.out.println("3: Volver");
			System.out.println("4: Desconectar y cerrar");
			scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			switch (opcion) {
			case 1:
				mostrarRO();
				break;
			case 2:
				mostrarRUEE();
				break;
			case 3:
				eleccionSentencia();
				break;
			case 4:
				System.out.println("HASTA LA PRÓXIMA");
				break;
			default:
				System.out.println("Esta opción no es válida");
				eleccionSentencia();
				break;
			}
			break;
		case 2:
			insertar();
			break;
		case 3:
			actualizar();
			break;
		case 4:
			try {
				@SuppressWarnings("resource")
				Scanner scannerBorrar = new Scanner(System.in);
				System.out.println("Introduzca el código de Residencia que quiere eliminar:");
				int codigo = scannerBorrar.nextInt();
				borrar(codigo);
			} catch (Exception e) {
				System.out.println("ERROR");
				eleccionSentencia();
			}
			break;
		case 5:
			System.out.println("HASTA LA PRÓXIMA");
			break;
		default:
			System.out.println("Esta opción no es válida");
			eleccionSentencia();
			break;
		}
	}

	private static void actualizar() {
		try {
			System.out.println("Código de residencia a actualizar: ");
			Residencias res1 = sesion.find(Residencias.class, scanner.nextInt());
			scanner.nextLine();
			System.out.println("Nombre residencia (Actual: "+res1.getNomResidencia()+" , Enter para no actualizar): ");
			String str = scanner.nextLine();
			if (!str.isEmpty()) {
				res1.setNomResidencia(str);
			}
			System.out.println("Comedor (Actual: "+res1.isComedor()+") (0->False / 1->True / 2->Para no actualizar campo): ");
			int p = scanner.nextInt();
			if (p == 0) {
				res1.setComedor(false);
			} else if (p == 1){
				res1.setComedor(true);
			}
			System.out.println("¿Quiere cambiar el precio mensual? (0 -> NO / 1 -> SI)");
			p = scanner.nextInt();
			if (p == 1) {
				System.out.println("Precio mensual (Actual: "+res1.getPrecioMensual()+")");
				res1.setPrecioMensual(scanner.nextInt());
				scanner.nextLine();
			}
			sesion.beginTransaction();
			sesion.update(res1);
			sesion.getTransaction().commit();
			eleccionSentencia();
		} catch (Exception e) {
			System.out.println("ERROR AL ACTUALIZAR");
			eleccionSentencia();
		}
	}

	
	@SuppressWarnings("unchecked")
	private static void mostrarRO() {
		try {
			sesion.beginTransaction();
			String sel = "SELECT r.codResidencia, r.nomResidencia, r.codUniversidad, r.precioMensual, r.comedor, ro.observaciones FROM Residencias r LEFT JOIN ResidenciasObservaciones ro ON r.codResidencia = ro.codResidencia";
			List<Object[]> results = sesion.createQuery(sel).getResultList();
			System.out.println();
			System.out.println(
					"codResidencia / nomResidencia / codUniversidad / precioMensual / comedor / observaciones");
			Universidades uni = new Universidades();
			for (Object[] result : results) {
				uni = (Universidades) result[2];
				if (result[5] == null) {
					System.out.println(result[0] + " / " + result[1] + " / " + uni.getCodUniversidad() + " / "
							+ result[3] + " / " + result[4] + " / No hay observaciones");
				} else {
					System.out.println(result[0] + " / " + result[1] + " / " + uni.getCodUniversidad() + " / "
							+ result[3] + " / " + result[4] + " / " + result[5]);
				}
			}
			sesion.getTransaction().commit();
			eleccionSentencia();
		} catch (Exception e) {
			System.out.println("ERROR AL MOSTRAR LOS DATOS");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void mostrarRUEE() {
		try {
			sesion.beginTransaction();
			Query cons = sesion.createQuery("from Estancias e");
			List<Estancias> listEstancias = cons.list();
			System.out.println();
			System.out.println(
					"codResidencia / nomResidencia / codUniversidad / nomUniversidad / precioMensual / comedor / codEstudiante / DNI / nomEstudiante / codEstancia / fechaInicio / fechaFin / preciopagado");
			for (Estancias e : listEstancias) {
				System.out.println(e.getCodResidencia().getCodResidencia() + " / " + e.getCodResidencia().getNomResidencia() + " / "
						+ e.getCodResidencia().getCodUniversidad().getCodUniversidad() + " / "
						+ e.getCodResidencia().getCodUniversidad().getNomUniversidad() + " / "
						+ e.getCodResidencia().getPrecioMensual() + " / " + e.getCodResidencia().isComedor() + " / "
						+ e.getCodEstudiante().getCodEstudiante() + " / " + e.getCodEstudiante().getDni() + " / "
						+ e.getCodEstudiante().getNomEstudiante() + " / " + e.getCodEstancia() + " / "
						+ e.getFechaInicio() + " / " + e.getFechaFin() + " / " + e.getPrecioPagado());
			}
			sesion.getTransaction().commit();
			eleccionSentencia();
		} catch (Exception e) {
			System.out.println("ERROR AL MOSTRAR LOS DATOS");
			eleccionSentencia();
		}
	}

	private static void insertar() {
		try {
			System.out.println();
			System.out.println("Elija una acción(debe introducir el número):");
			System.out.println("1: Insertar Residencia");
			System.out.println("2: Insertar Universidad");
			System.out.println("3: Insertar Estancia");
			System.out.println("4: Insertar Estudiante");
			System.out.println("5: Insertar Observación");
			System.out.println("6: Volver");
			System.out.println("7: Desconectar y cerrar");
			scanner = new Scanner(System.in);
			opcion = scanner.nextInt();
			scanner.nextLine();
			switch (opcion) {
			case 1:
				sesion.beginTransaction();
				Residencias r = new Residencias();
				System.out.println("Nombre Residencia:");
				r.setNomResidencia(scanner.nextLine());
				Universidades uni = new Universidades();
				System.out.println("Código Universidad:");
				uni.setCodUniversidad(scanner.nextLine());
				r.setCodUniversidad(uni);
				System.out.println("Precio Mensual:");
				r.setPrecioMensual(scanner.nextInt());
				scanner.nextLine();
				System.out.println("Comedor (Si -> 1 / No -> Cualquier número distinto de 1):");
				if (scanner.nextInt() == 1)
					r.setComedor(true);
				else
					r.setComedor(false);
				scanner.nextLine();
				sesion.save(r);
				sesion.getTransaction().commit();
				eleccionSentencia();
				break;
			case 2:
				sesion.beginTransaction();
				Universidades uni2 = new Universidades();
				System.out.println("Código Universidad:");
				uni2.setCodUniversidad(scanner.nextLine());
				System.out.println("Nombre Universidad:");
				uni2.setNomUniversidad(scanner.nextLine());
				sesion.save(uni2);
				sesion.getTransaction().commit();
				eleccionSentencia();
				break;
			case 3:
				sesion.beginTransaction();
				Estancias e = new Estancias();
				System.out.println("Precio pagado:");
				e.setPrecioPagado(scanner.nextInt());
				scanner.nextLine();
				System.out.println("Fecha de inicio (YYYY-MM-DD):");
				e.setFechaInicio(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));
				System.out.println("Fecha fin (YY-MM-DD): ");
				e.setFechaFin(new SimpleDateFormat("yyyy-MM-dd").parse(scanner.nextLine()));
				Estudiantes estu = new Estudiantes();
				System.out.println("Código del estudiante:");
				estu.setCodEstudiante(scanner.nextInt());
				scanner.nextLine();
				e.setCodEstudiante(estu);
				Residencias r2 = new Residencias();
				System.out.println("Código del residencia:");
				r2.setCodResidencia(scanner.nextInt());
				scanner.nextLine();
				e.setCodResidencia(r2);
				sesion.save(e);
				sesion.getTransaction().commit();
				eleccionSentencia();
				break;
			case 4:
				sesion.beginTransaction();
				Estudiantes estu2 = new Estudiantes();
				System.out.println("DNI:");
				estu2.setDni(scanner.nextLine());
				System.out.println("Nombre Estudiante:");
				estu2.setNomEstudiante(scanner.nextLine());
				System.out.println("Teléfono:");
				estu2.setTelefonoEstudiante(scanner.nextLine());
				sesion.save(estu2);
				sesion.getTransaction().commit();
				eleccionSentencia();
				break;
			case 5:
				try {
					sesion.beginTransaction();
					ResidenciasObservaciones resOB = new ResidenciasObservaciones();
					Residencias resi3 = new Residencias();
					System.out.println("Código Residencias:");
					resi3.setCodResidencia(scanner.nextInt());
					scanner.nextLine();
					resOB.setCodResidencia(resi3);
					System.out.println("Observaciones(200 caracteres máx.):");
					resOB.setObservaciones(scanner.nextLine());
					sesion.save(resOB);
					sesion.getTransaction().commit();
				} catch (Exception e1) {
					System.out.println("Esta residencia ya tiene observaciones");
				}
				eleccionSentencia();
				break;
			case 6:
				eleccionSentencia();
				break;
			case 7:
				System.out.println("HASTA LA PRÓXIMA");
				break;
			default:
				System.out.println("Esta opción no es válida");
				eleccionSentencia();
				break;
			}
		} catch (Exception e) {
			sesion.getTransaction().commit();
			System.out.println("ERROR AL INSERTAR EL DATO");
			eleccionSentencia();
		}
	}

	private static void borrar(int codigo) {
		try {
			System.out.println("Va a borrar la Residencia con código " + codigo
					+ ", además se borrarán las estancias y observaciones de la misma, ¿está seguro? (S/N)");
			scanner = new Scanner(System.in);
			String afirmar = scanner.nextLine();
			if (afirmar.equals("S")) {
				sesion.beginTransaction();
				Residencias residencia = sesion.find(Residencias.class, codigo);
				residencia.getEstancias().clear();
				sesion.close();

				Session session = HibernateUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				ResidenciasObservaciones resiOb = new ResidenciasObservaciones();
				resiOb.setCodResidencia(residencia);
				session.delete(resiOb);
				session.delete(residencia);
				transaction.commit();
				session.close();
				sesion = HibernateUtil.getSessionFactory().openSession();
				eleccionSentencia();
			} else if (afirmar.equals("N")) {
				System.out.println("Operación cancelada");
				eleccionSentencia();
			} else {
				System.out.println("Opción no válida");
				borrar(codigo);
			}
		} catch (Exception e) {
			System.out.println("EL DATO NO EXISTE");
			eleccionSentencia();
		}
	}

}