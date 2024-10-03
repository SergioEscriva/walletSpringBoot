package dev.sergioescriva.wallet.utilities;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import dev.sergioescriva.wallet.models.Transaction;
import dev.sergioescriva.wallet.models.User;

public class Settle {

    private static List<Transaction> listaDeTransactions;
    private static List<User> listaDeUsers;
    private static List<String> listaDeParticipan;
    private static Map<Long, Double> listaDeGastos;
    private static long walletId = 1;

    // listaDeTransactions = new ArrayList<>();
    // listaDeUsers = new ArrayList<>();
    // listaDeParticipan = new ArrayList<>();
    // listaDeGastos = new HashMap<>();

    // Esta es la suma que aparece en el resumen.
    // INICIA TODAS LAS VARIABLES NECESARIAS PARA OPERAR EN ESTA CLASE
    public ArrayList<ArrayList> sumaTransactions(List<Transaction> listaDeTransactions, List<User> listaDeUsers) {
        listaDeParticipan = new ArrayList<>();

        Settle.listaDeTransactions = listaDeTransactions;
        Settle.listaDeUsers = listaDeUsers;

        for (User participan : listaDeUsers) {
            listaDeParticipan.add(participan.getUsername().toString());
        }

        double total = 0.0;
        for (Transaction transaction : listaDeTransactions) {
            double totalLimpiar = transaction.getAmount();
            total += Double.valueOf(totalLimpiar);
        }

        proximoPagador();
        pagadoPorCadaUser();

        return resolucionDeudaWallet();
    }

    // #1
    public ArrayList<ArrayList> resolucionDeudaWallet() {
        System.out.println("ResolucionesWallet funcionaaaaaaaaa");
        Operations operaciones = new Operations();
        // Recuperamos deudas por Wallet
        HashMap<Long, String> usuarioIdNombbre = new HashMap<>();
        for (User user : listaDeUsers) {
            String nombre = user.getUsername();
            long userId = user.getId();
            usuarioIdNombbre.put(userId, nombre);
        }

        // Iniciamos variables
        Map<Long, Double> pagarUser = new HashMap<>();
        Map<Long, Double> recibirUser = new HashMap<>();
        ArrayList<Map> gastoUsers = gastosUsersTransactions(listaDeTransactions);
        Map<Long, Double> deudas = unificaGastoUserWallet(gastoUsers); // #2

        // Extrae las Keys de las transacciones para los cálculos y rellenamos
        // variables.
        deudas.keySet().forEach((key) -> {
            double pagar = 0L;
            double recibir = 0L;
            long userId = Long.parseLong(key.toString());
            long iterarKey = userId;
            double cantidadParticipa = Double.parseDouble(String.valueOf(deudas.get(iterarKey)));

            // Separamos pagar y recibir en dos listas.
            if (cantidadParticipa >= 0L) {
                double recibirDecimales = operaciones.dosDecimalesDoubleDouble(cantidadParticipa);
                recibir = recibirDecimales;
            } else if (cantidadParticipa != 0L) {
                double pagarDecimales = operaciones.dosDecimalesDoubleDouble(cantidadParticipa);
                pagar = pagarDecimales;
            }
            pagarUser.put(userId, pagar);
            recibirUser.put(userId, recibir);
        });

        // Ordenamos pagos de mayor a menor
        Map<Long, Double> pagarOrdenado = ordenarTransactions(pagarUser); // #6
        Map<Long, Double> recibirOrdenado = ordenarTransactions(recibirUser); // #6

        // Creamos un diccionario para almacenar las soluciones
        ArrayList<ArrayList> soluciones = new ArrayList<>();

        // Iteramos sobre la lista de los que tienen que pagar(pagador)
        for (long pagarId : pagarOrdenado.keySet()) {
            double cantidadAPagar = operaciones.dosDecimalesDoubleDouble(pagarOrdenado.get(pagarId));
            long pagador = pagarId;

            // Iteramos sobre la lista de los que tienen que recibir(cobrador)
            while (Math.abs(cantidadAPagar) > 0) {
                for (long recibirId : recibirOrdenado.keySet()) {
                    double cantidadARecibirLimpiar = recibirOrdenado.get(recibirId);
                    double cantidadARecibir = operaciones.dosDecimalesDoubleDouble(cantidadARecibirLimpiar);
                    long cobrador = recibirId;

                    // Si el cobrador debe recibir más de lo que el pagador tiene que pagar
                    if (cantidadARecibir > Math.abs(cantidadAPagar)) {
                        double cantidadAPagarIni = cantidadAPagar;

                        // El pagador paga la cantidad que debe al cobrador
                        ArrayList<String> resoluciones = new ArrayList<>();
                        resoluciones.add(String.valueOf(pagador));
                        resoluciones.add(String.valueOf(usuarioIdNombbre.get(pagador)));
                        resoluciones.add(String.valueOf(cobrador));
                        resoluciones.add(String.valueOf(usuarioIdNombbre.get(cobrador)));
                        resoluciones.add(String.valueOf(cantidadAPagar));
                        soluciones.add(resoluciones);

                        // Actualizamos la cantidad que el cobrador tiene que recibir
                        double recibirCalculadoLimpiar = cantidadARecibir + cantidadAPagar;
                        double recibirCalculado = operaciones.dosDecimalesDoubleDouble(recibirCalculadoLimpiar);
                        recibirOrdenado.replace(recibirId, recibirCalculado);

                        // El pagador ya no tiene que pagar nada
                        cantidadAPagar = 0;
                        pagarOrdenado.replace(pagador, cantidadAPagar);
                        break;
                    }
                    // Si el cobrador debe recibir menos o lo mismo de lo que el pagador
                    // tiene que pagar
                    if (Math.abs(cantidadAPagar) == 0) {
                        break;
                    }
                    if (Math.abs(cantidadAPagar) > 0) {
                        // double cantidadAPagarIni = cantidadAPagar;

                        // El pagador paga la cantidad que el receptor tiene que recibir
                        ArrayList<String> resoluciones = new ArrayList<>();
                        resoluciones.add(String.valueOf(pagador));
                        resoluciones.add(String.valueOf(usuarioIdNombbre.get(pagador)));
                        resoluciones.add(String.valueOf(cobrador));
                        resoluciones.add(String.valueOf(usuarioIdNombbre.get(cobrador)));
                        resoluciones.add(String.valueOf(Math.abs(cantidadARecibir)));
                        soluciones.add(resoluciones);

                        // Actualizamos la cantidad que el pagador tiene que pagar
                        cantidadAPagar += cantidadARecibir;

                        // El cobrador ya no tiene que recibir nada
                        recibirOrdenado.remove(cobrador, cantidadARecibir);

                        pagarOrdenado.replace(pagador, cantidadAPagar);
                        break;
                    }
                }
                if (recibirOrdenado.size() == 0) {
                    break;

                }
            }

        }
        ArrayList<ArrayList> solucionesLimpias = eliminarSolucionesCero(soluciones); // #7
        // Rellenar Gastos Totales
        System.out.println("Estas son las soluciones " + solucionesLimpias);
        return solucionesLimpias;
    }

    // #2
    public Map<Long, Double> unificaGastoUserWallet(ArrayList<Map> gastoUsers) {
        // ArrayList<Map> gastoUsers = gastosUsersTransactions(); //#3
        Map<Long, Double> gastosParticianTotalesWallet = new HashMap<Long, Double>();
        try {
            // Iteramos en busca de las keys
            for (long l = 0; l < gastoUsers.get(0).size(); l++) {

                // Extrae las Keys de las transacciones

                gastoUsers.get(0).keySet().forEach((key) -> {
                    long userId = Long.parseLong(key.toString());
                    long iterar = userId;

                    // Suma todas las keys values del mismo User
                    DoubleSummaryStatistics sumaValoresImporte = gastoUsers
                            .stream()
                            .collect(Collectors.summarizingDouble(
                                    e -> Double.valueOf(e.get(iterar).toString())));

                    double importeTotal = sumaValoresImporte.getSum();
                    gastosParticianTotalesWallet.put(iterar, importeTotal);
                    listaDeGastos = gastosParticianTotalesWallet;

                });

            }
        } catch (Exception e) {
        }
        return gastosParticianTotalesWallet;
    }

    // #3 que debería pagar cada user
    public ArrayList<Map> gastosUsersTransactions(List<Transaction> listaDeTransactionsNew) {
        Operations operaciones = new Operations();
        ArrayList<Map> gastoUsers = new ArrayList<>();

        // iteramos transacciones sacamos a lo que sale cada user
        for (Transaction unaTransaction : listaDeTransactionsNew) {

            Map<Long, Double> pagadoPorUser = pagadoPorCadaUser(unaTransaction); // #4
            Transaction deudaTotal = unaTransaction;
            Double aPagarPorUser = aPagarPorUser(unaTransaction); // #5

            // Extraemos lo que ha pagado cada user
            Map<Long, Double> deudas = new HashMap<Long, Double>();
            for (int n = 0; n < listaDeUsers.size(); n++) {
                long userId = listaDeUsers.get(n).getId();
                double pagado = pagadoPorUser.get(userId);

                // segun se haya pagado o no una cantidad se resta
                // Comprueba si existe en la lista de users y asigna importes, los demás
                // a cero
                String listado = unaTransaction.getParticipants();
                String userIdInt = String.valueOf(userId);
                int existeEnListas1 = listado.indexOf(userIdInt);

                double saldo = pagado;
                // NO ha pagado esta transacción pero está en ella
                if (pagado == 0.0 && existeEnListas1 >= 0) {
                    double saldoDecimales = pagado - aPagarPorUser;
                    saldo = operaciones.dosDecimalesDoubleDouble(saldoDecimales);
                    deudas.put(userId, saldo);

                    // SI pagado la transacción y está en ella
                } else if (pagado > 0.0 && existeEnListas1 >= 0) {
                    double saldoDecimales = pagado - aPagarPorUser;
                    saldo = operaciones.dosDecimalesDoubleDouble(saldoDecimales);
                    deudas.put(userId, saldo);

                    // No está en la transacción
                } else {
                    saldo = pagado;

                }
                deudas.put(userId, saldo);
            }

            gastoUsers.add(deudas);
        }

        return gastoUsers;
    }

    // #4 Suma todos los pagos por user
    public Map<Long, Double> pagadoPorCadaUser(Transaction transaction) {
        long transactionId = transaction.getId();

        // Creamos un diccionario con lo que ha pagado cada user de esta transacción
        Map<Long, Double> datos = new HashMap<Long, Double>();
        long nombreId = transaction.getUserId();
        double importe = Double.valueOf(transaction.getAmount());
        for (User unNombre : listaDeUsers) {
            long nombreIdIndividual = unNombre.getId();
            double importeCero = 0.0;
            datos.put(nombreIdIndividual, importeCero);
        }
        datos.put(nombreId, importe);

        return datos;
    }

    // #5 Se calcula que debería pagar cada user.
    public double aPagarPorUser(Transaction transaction) {
        Operations operaciones = new Operations();

        // Calculamos la deuda total
        double numeroUsers = listaDeParticipan(transaction);

        double importeTransaction = Double.valueOf(transaction.getAmount());
        double importePorUserLimpiar = importeTransaction / (numeroUsers + 0);
        double importePorUser = operaciones.dosDecimalesDoubleDouble(importePorUserLimpiar);
        return importePorUser;
    }

    // #6
    public Map<Long, Double> ordenarTransactions(Map<Long, Double> transactions) {

        // Ordenamos pagos de mayor a menor
        // Java 8 Stream (https://www.techiedelight.com/es/sort-map-by-values-java/)
        // https://www.techiedelight.com/es/sort-map-java-reverse-ordering-keys/
        Map<Long, Double> ordenarDiccionario = new LinkedHashMap<>();

        transactions.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(entry -> ordenarDiccionario.put(entry.getKey(), entry.getValue()));

        return ordenarDiccionario;
    }

    // #7
    public ArrayList<ArrayList> eliminarSolucionesCero(ArrayList<ArrayList> soluciones) {
        ArrayList<ArrayList> solucionesLimpias = new ArrayList<>();

        // Iteramos y eliminamos los que tienen que pagar 0 €
        for (ArrayList solucion : soluciones) {
            String importe = solucion.get(4).toString();
            double importeLong = Double.parseDouble(importe);
            if (importeLong != 0D) {
                solucionesLimpias.add(solucion);
            }
        }
        return solucionesLimpias;
    }

    // Compone parte de la pantalla de Gastos Totales.
    public ArrayList<String> operacionesResolucionDeudas() {
        Operations operaciones = new Operations();
        ArrayList<ArrayList> gastosTotalesDivididos = new ArrayList<>();

        // Creamos nuevas listas pero sin los gastos que se han pagado para uno mismo.
        Map<Long, Double> importePagadoParticipante = transacionesGastosTotales();
        List<Transaction> listaDeTransactionsSinPropio = listaDeTransactionsSinPropio();
        ArrayList<Map> gastoUsers = gastosUsersTransactions(listaDeTransactionsSinPropio);
        Map<Long, Double> listaDeGastosSinPropio = unificaGastoUserWallet(gastoUsers);
        ArrayList<String> usersGastos = new ArrayList<>();

        // Iteramos sobre los gastos para extraer que tendría que haber pagado cada
        // user.
        // donde no estára incluido lo que se haya pagado sólo a si mismo.
        for (Long userIdGasto : listaDeGastosSinPropio.keySet()) {
            double importeDeberiaPagarAlWallet = listaDeGastosSinPropio.get(userIdGasto);

            // Limpiamos decimales del importe
            double importeMovimientosWallet = operaciones.dosDecimalesDoubleDouble(importeDeberiaPagarAlWallet);

            // Obtenemos de la listaDeGastos los ids, y los iteramos con la listaDeUsers,
            // para obtener el nombre.
            for (User solucionFinal : listaDeUsers) {
                long userId = solucionFinal.getId();
                String user = solucionFinal.getUsername();

                // Añadimos los gastos o cobros por participantes.
                if (userIdGasto == userId) {

                    // if (importeDeberiaPagarAlWallet == userId) {
                    // String user = new String();
                    String importeString = "";

                    // Rescatamos importe pagado por cada user
                    double importeHaPagado = importePagadoParticipante.get(solucionFinal.getId());
                    String importeHaPagadoString = operaciones.dosDecimalesDoubleString(importeHaPagado);

                    // Rescatamos importes a pagar o recibir.
                    String importeFinalDebe = "";
                    String importeFinalPagado = "";
                    double importeDoubleLimpio = 0;
                    double importeFinalPagadoLimpio = 0;
                    double gastoRealizado = 0;

                    // Según sea a pagar o recibir se separan para poder mostrarlos.
                    if (importeMovimientosWallet <= 0D) {
                        importeDoubleLimpio = Math.abs(importeMovimientosWallet);
                        String limpiezaNumero = String.valueOf(importeDoubleLimpio);
                        if (importeDoubleLimpio <= 0D) {
                            importeFinalDebe = "No tiene deudas.";
                        } else {

                            importeFinalDebe = "<b>Debe </b><FONT COLOR=#E91E63>" + limpiezaNumero + "€</FONT>";
                        }
                        // Calculamos lo que el gasto total de cada user en el Wallet
                        double gastoRealizadoLimpiar = importeDoubleLimpio + importeHaPagado;
                        gastoRealizado = operaciones.dosDecimalesDoubleDouble(gastoRealizadoLimpiar);
                    } else {
                        importeFinalPagadoLimpio = importeMovimientosWallet;
                        importeString = String.valueOf(importeFinalPagadoLimpio);
                        importeFinalPagado = "<b>Le deben </b><FONT COLOR=#1ED63A>" + importeString + "€</FONT>";

                        // Calculamos lo que ha gasto total de cada user en el Wallet
                        double gastoRealizadoLimpiar = importeHaPagado - importeMovimientosWallet;
                        gastoRealizado = operaciones.dosDecimalesDoubleDouble(gastoRealizadoLimpiar);
                    }
                    String gastoRealizadoEnWallet = String.valueOf(Math.abs(gastoRealizado));

                    String userGastoString = ("<strong>" + user + "</strong> adeuda <b>" + gastoRealizadoEnWallet
                            + "€</b><br><i>     Ha pagado " + importeHaPagadoString + "€</i><br>" + importeFinalDebe
                            + importeFinalPagado);
                    usersGastos.add(userGastoString);
                    ArrayList<String> gastosTotales = new ArrayList<>();
                    gastosTotales.add(user);
                    gastosTotales.add(importeString);
                    gastosTotalesDivididos.add(gastosTotales);
                    // }
                }

            }
        }
        System.out.println("Sería el resultado Final" + usersGastos);
        return usersGastos;
    }

    // Gastos que ha realizado cada participante en las transacciones del wallet
    public Map<Long, Double> transacionesGastosTotales() {
        Map<Long, Double> gastoTotalpagador = new HashMap<>();

        // lista de miembrps sin formato
        listaDeUsers();

        // Creamos una diccionario con todos los participantes del Wallet, y los ponemos
        // a 0 gastado
        double pagadorImporte = 0.0D;
        long pagadorId = 0;
        for (User solucionFinal : listaDeUsers) {
            long userId = solucionFinal.getId();
            gastoTotalpagador.put(userId, pagadorImporte);
        }
        // Ahora añadimos lo que realmente ha pagado cada uno.

        for (Transaction transaction : listaDeTransactions) {
            pagadorId = transaction.getUserId();
            double importePrevio = gastoTotalpagador.get(pagadorId);
            importePrevio += Double.parseDouble(transaction.getAmount().toString());
            gastoTotalpagador.replace(pagadorId, importePrevio);
        }
        return gastoTotalpagador;
    }

    public List<String> listaDeUsers() {
        List<String> lista = new ArrayList<>();

        for (User listaCompleta : listaDeUsers) {
            String nombre = listaCompleta.getUsername();
            lista.add(nombre);
        }
        return lista;
    }

    // rellenamos la lista de particiapntes de cada transacción.
    // List<String>
    public int listaDeParticipan(Transaction transaction) {
        List<String> lista = new ArrayList<>();

        String participante = "";
        String participantes = transaction.getParticipants().toString();
        String[] listaNumeroDecimal = participantes.split(",");
        int numeroParticipantes = listaNumeroDecimal.length;
        for (String unParticipante : listaNumeroDecimal) {
            participante = unParticipante;
        }
        lista.add(participante);
        listaDeParticipan = lista;
        return numeroParticipantes;
    }

    public User proximoPagador() {
        Map<Long, Double> listaPagadoresId = pagadoPorCadaUser();
        Map<Long, Double> pagadoresIdOrdenados = ordenarTransactions(listaPagadoresId);

        Set<Long> pagadoresId = pagadoresIdOrdenados.keySet();
        long pagadorFinalId = pagadoresId.iterator().next();
        // List<String> siguientePagador = new ArrayList<>();
        User siguientePagador = new User();
        for (User users : listaDeUsers) {
            if (pagadorFinalId == users.getId()) {
                // siguientePagador.add(String.valueOf(pagadorFinalId));
                // siguientePagador.add(users.getNombre());
                String siguientePagadorNombre = users.getUsername();
                // siguientePagador = new User(siguientePagadorNombre, siguientePagadorNombre,
                // pagadorFinalId);
                siguientePagador = new User();
                siguientePagador.setUsername(siguientePagadorNombre);
                siguientePagador.setUsername(siguientePagadorNombre);
                siguientePagador.setId(pagadorFinalId);
            }
        }
        return siguientePagador;
    }

    public Map<Long, Double> listaUsersACero() {
        // Añade users y les pone valor 0.0
        Map<Long, Double> datos = new HashMap<Long, Double>();
        for (User participaIdItera : listaDeUsers) {
            long userId = participaIdItera.getId();
            double importeCero = 0.0;
            datos.put(userId, importeCero);
        }
        return datos;
    }

    public Map<Long, Double> pagadoPorCadaUser() {
        Map<Long, Double> datos = listaUsersACero();
        for (Transaction transaction : listaDeTransactions) {

            // Creamos un diccionario con lo que ha pagado cada user cada una de las
            // transacciones
            double importeAnterior = 0;
            double importeTransaction = 0;
            double importeNuevo = 0;
            long pagadorId = 1;
            for (User participa : listaDeUsers) {

                pagadorId = transaction.getUserId();
                importeAnterior = datos.get(pagadorId);
                importeTransaction = Double.valueOf(transaction.getAmount());

                // Suma al valor anterior el nuevo valor gastado si lo hay
                importeNuevo = importeAnterior + importeTransaction;
            }
            datos.put(pagadorId, importeNuevo);
        }
        return datos;
    }

    // sin los Propios pagos sólo a si mismo para Gastos totales

    // lista de transacciones sin los Propios pagos sólo a si mismo para Gastos
    // totales
    public List<Transaction> listaDeTransactionsSinPropio() {
        List<Transaction> listaDeTransactionsSinPropio = new ArrayList<>();

        // iteramos sobre los gastos y si es el pagador y participante el mismo no se
        // añade.
        for (Transaction transaction : listaDeTransactions) {
            long pagadorId = transaction.getUserId();
            String listaParticipantes = transaction.getParticipants();
            if (listaParticipantes.length() == 1 && listaParticipantes.contains(String.valueOf(pagadorId))) {

            } else {
                listaDeTransactionsSinPropio.add(transaction);
            }
        }
        return listaDeTransactionsSinPropio;
    }

    // Formatea e importe para mostrarlo según España.
    // https://javiergarciaescobedo.es/programacion-en-java/29-trucos/113-formato-de-numeros-monedas-y-porcentajes2
    public String importeFormateado(String importeLimpiar) {
        double importe = Double.valueOf(importeLimpiar);
        NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();
        // Si se desea forzar el formato español:
        formatoImporte = NumberFormat.getCurrencyInstance(new Locale("es", "ES"));
        return formatoImporte.format(importe);
    }

}
