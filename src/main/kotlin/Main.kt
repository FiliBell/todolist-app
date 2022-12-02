import java.util.ArrayList
import java.io.File //serve per poter implementare la funzione File() per la creazione di file.txt
import java.lang.Exception

/*

Traccia base: APP - To do list ELEMENTI BASE - Deve partire e far inserire le task e dare la possibilità di segnarle come completate
- Deve avere un sistema CRUD isolato (richiamato da funzione)
- Le task devono essere integrate con tutte le funzioni crud funzionanti su le medesime
- Il sistema si deve chiudere previo conferma

Il minimo e' l'inserimento di due delle Feature possibili:

- Collegamento parziale DB (HARD){una sfida per chi vuole osare}
- Test unitario delle funzioni CRUD o test della UI (HARD) {Un sistema molto delicato ma che può rivelarsi molto utile in ottica di strutturazione della mentalità di programmazione}
- Introduzione elementi grafici (EASY oppure MEDIUM con elementi animati) {Introduzione della grafica minima indispensabile atta alle funzionalità o elementi di contorno funzionali animati}
- Login system funzionante (MEDIUM) {possibilità di recuperare la password, nome unico e richiesta dell'email anch'essa con obbligo di unicità}
- Impostazioni funzionanti (MEDIUM) {Permettere tramite un input di poter accedere ad alcune impostazioni es: colore sfondo, numero task a vista}
- Report in vari formati (EASY txt, json) {far si che si possa esportare la to do list in vari formati}
- Composizione di pacchetti dati (EASY) {è legato col login ed la possibilità di andare ad utilizzare i dati privati dell'utente, come la password, senza conoscerli in maniera diretta}
- Elementi della lista DO a step (EASY) {far si che si possono creare anche task che abbiamo più di una fase}
- Rendere possibile avere gruppi di to do list nominativi(EASY){far si che si possa dal menu iniziale scegliere se e a quale gruppo di to-do-list accedere dopo aver fatto visualizzare i nomi se esistenti delle liste di to-do}
- Possibilità di introduzione di elementi secondari concordati (JOLLY)

 */

//ARRAY
var utentiTotali : ArrayList<User> = ArrayList()    //array contenente gli utenti totali iscritti
var tasks : ArrayList<ToDo> = ArrayList()           //array contenente tutte le task di tutti gli utenti

//CLASSI
class ToDo{ //classe che crea l'oggetto che conterra' i valori delle task
    var id = 0  //serve per identificare le task di un dato utente
    var name = ""
    var status : Boolean = false    //serve per definire se una task viene inserita tra i preferiti o no (true --> PREFERITI)
}

class User {    //classe che crea l'oggetto che conterra' i valori degli utenti
    var id = utentiTotali.size + 1
    /*
    ID -->serve per associare le task all'utente. Al momento della creazione di un nuovo utente l'ID prendera' il primo valore possibile, ovvero
          il valore successivo alla dimensione dell'array utentiTotali
     */
    var username = " "
    var password = " "
    var email = " "
}

//CRUD
fun Create(x : String, y : Boolean, z : User){  //ha la funzione di creare una task

    var contenitore : ToDo = ToDo() //variabile di appoggio che contiene i valori inseriti da terminale
    contenitore.name = x
    contenitore.status = y
    contenitore.id = z.id
    tasks.add(contenitore)

}

fun Read(x : Int, y : User, z : ToDo): ToDo{    //ha la funzione di leggere dall'array delle task un particolare elemento e ritornarlo

    var contenitore : ToDo = ToDo() //variabile di appoggio
    var contatore = 0   //serve per cercare il riferimento richiesto dall'utente delle sole task visibili (le task create dall'utente stesso)
    for (i in 0 until tasks.size){
        if ((tasks.get(i).id).equals(y.id)){    //ricerca di ID uguali a quelli dell'utente nell'array delle task
            contatore = contatore + 1
            if (contatore == x){    //momento in cui viene trovato un elemento inserito dall'utente che ha il riferimento giusto
                contenitore = tasks.get(i)
            }
            else{
                continue
            }
        }
        else{
            continue
        }
    }
    return contenitore

}

fun Update(x : Int, y : ToDo, z : User){    //ha la funzione di modificare una particolare task

    var contenitore : ToDo = ToDo() //variabile di appoggio
    contenitore.name = y.name
    contenitore.status = y.status
    contenitore.id = z.id

    var contatore = -1  //serve per eliminare l'emento giusto corrispondente al riferimento scelto
    for (i in 0 until tasks.size){
        if ((tasks.get(i).id).equals(z.id)){
            contatore = contatore + 1
            if (contatore == x){
                tasks.set(i, contenitore)
            }
            else{
                continue
            }
        }
        else{
            continue
        }
    }

}

fun Delete(x : Int, y : User){  //ha la funzione di eliminare una particolare task

    var contatore = -1
    for (i in 0 until tasks.size){
        if ((tasks.get(i).id).equals(y.id)){
            contatore = contatore + 1
            if (contatore == x){
                tasks.removeAt(i)
                break
            }
            else{
                continue
            }
        }
        else{
            continue
        }
    }

}

fun Controllo(x : User): Boolean{   //ha la funzione di controllare se esiste gia' un utente con una data email e un dato username
    var risultato : Boolean = false //serve per ritornare vero o falso
    for (i in 0 until utentiTotali.size){
        if (x.username.equals(utentiTotali.get(i).username) || x.email.equals(utentiTotali.get(i).email)){
            //cerca nell'array se e' presente un email o un username uguale
            println("Username o Email gia' in uso! Riprova")
            risultato = true
            break
        }
        else{
            continue
        }
    }
    return risultato
}

fun StampaTasks(x : User){  //ha la funzione di stampare a terminale l'insieme delle task di un dato utente

    var contatore = 0
    for (i in 0 until tasks.size){
        if ((x.id).equals(tasks.get(i).id)){    //controllo dell'ID delle task uguale a quello dell'utente
            contatore = contatore + 1
            println("Task numero ${contatore}: ")
            print("nome task: ")
            println(tasks.get(i).name)
            print("stato task: ")
            println(tasks.get(i).status)
            print("id: ")
            println(tasks.get(i).id)
        }
        else{
            continue
        }
    }

}

fun StampaTxt(x : User, y : String){    //ha la funzione di stampare un file.txt contenente l'insieme delle task di un dato utente

    try {   //operazione che puo' lanciare eccezioni
        val fileName = "${y}.txt"
        val myFile = File(fileName) //creo un file di nome scelto dall'utente
        myFile.printWriter().use {  //tutto quello inserito all'interno di questa graffa che contiene un out.print verra' salvato nel file.txt
                out->
            out.println("Lista delle Task di utente ${x.id}: ")
            var contatore = 0
            for (i in 0 until tasks.size){  //ciclo che ha lo scopo di controllare nell'array le solo task di un dato utente e stamparle
                if ((x.id).equals(tasks.get(i).id)){
                    contatore = contatore + 1
                    out.println("Task numero ${contatore}: ")
                    out.print("nome task: ")
                    out.println(tasks.get(i).name)
                    out.print("stato task: ")
                    out.println(tasks.get(i).status)
                    out.print("id: ")
                    out.println(tasks.get(i).id)
                }
                else{
                    continue
                }
            }

        }
    }catch (e:Exception){   //gestione dell'eccezione
        e.printStackTrace()
    }finally {  //avvertimento di fine processo
        println("---------------------")
    }

}


fun main() {

    //INIZIALIZZAZIONE E DICHIARAZIONE VARIABILI
    var controlloApp: Boolean = false
    var controlloLogin: Boolean = false
    var controlloScelta: Boolean = false
    var controlloScelta1: Boolean = false
    var controlloDati: Boolean = false
    var controlloSicurezza: Boolean = false
    var controlloDimensione: Boolean = false
    var controlloMenuTask: Boolean = false
    var controlloAggiunta: Boolean = false
    var controlloModifica: Boolean = false
    var controlloElimina: Boolean = false

    var usernameTest: String = ""
    var passwordTest: String = ""
    var emailTest: String = ""
    var scelta0 = " "
    var scelta1 = " "
    var scelta2 = " "

    var appoggioUtenti: User = User()   //variabile di appoggio per sapere quale utente e' entrato nel menu' delle task

    while (controlloApp == false) { //ciclo che mi permette di tornare al login quando esco dal menu' delle task

        //LOGIN
        while (controlloLogin == false) {   //inizio ciclo che controlla il LOGIN

            println("----------LOGIN MENU----------")
            print("Buongiorno, sei gia' registrato? Scegli tra YES o NO: ")
            scelta0 = readLine()!!.toString()   //prendo in input la scelta dell'utente

            controlloScelta = false

            if (scelta0.equals("YES", ignoreCase = true)) { //CASO UTENTE GIA' REGISTRATO

                while (controlloScelta == false) {  //inizio ciclo che controlla la scelta tra ACCEDI e RECUPERA PASSWORD

                    print("Vuoi ACCEDERE o RECUPERARE LA PASSWORD? [ACCEDI][RECUPERA]: ")
                    scelta1 = readLine()!!.toString()   //prendo in input la scelta dell'utente

                    controlloDati = false

                    if (scelta1.equals("ACCEDI", ignoreCase = true)) {
                        print("Inserisci il tuo username: ")
                        usernameTest = readLine()!!.toString()  //prendo in input la scelta dell'utente

                        print("Inserisci la tua password: ")
                        passwordTest = readLine()!!.toString()  //prendo in input la scelta dell'utente

                        //controllo in caso l'utente provi ad accedere quando il l'array utentiTotali non contiene nessun elemento
                        if (utentiTotali.size == 0){
                            println("Non ci sono utenti registrati nel database")
                            controlloScelta = true
                        }

                        for (i in 0 until utentiTotali.size) {
                            if (usernameTest.equals(utentiTotali.get(i).username) && passwordTest.equals(
                                    utentiTotali.get(
                                        i
                                    ).password
                                )
                            ) { //controlla se username e password sono presenti nell'array degli utenti
                                appoggioUtenti = utentiTotali.get(i)    //aggiungo i dati dell'utente nella variabile di appoggio del controllo degli ID
                                println("Login avvenuto con successo")
                                controlloScelta = true
                                controlloLogin = true
                                break
                            } else {
                                println("username o password non corretti")
                            }
                        }
                    } else if (scelta1.equals("RECUPERA", ignoreCase = true)) { //CASO RECUPERO PASSWORD

                        while (controlloDati == false) {    //inizio controllo di email e username
                            print("Inserisci la tua email: ")
                            emailTest = readLine()!!.toString()

                            print("Inserisci il tuo username: ")
                            usernameTest = readLine()!!.toString()

                            for (i in 0 until utentiTotali.size) {
                                if (emailTest.equals(utentiTotali.get(i).email) && usernameTest.equals(
                                        utentiTotali.get(
                                            i
                                        ).username
                                    )
                                ) { //caso in cui username e email sono quelli corretti
                                    println("Ti abbiamo inviato per mail un link per recuperare la password")
                                    controlloDati = true
                                } else {
                                    println("Indirizzo email o username non trovati")
                                }
                            }
                        }   //fine ciclo controlloDati
                    } else {
                        println("Devi scegliere tra ACCEDI o RECUPERA! Riprova")    //messaggio di errore di inserimento
                    }
                }   //fine ciclo controlloScelta
            } else if (scelta0.equals("NO", ignoreCase = true)) {   //CASO UTENTE NON REGISTRATO

                while (controlloScelta1 == false) { //inizio ciclo controllo della scelta dell'utente

                    print("Inserisci il tuo username: ")
                    usernameTest = readLine()!!.toString()

                    print("Inserisci la tua password: ")
                    passwordTest = readLine()!!.toString()

                    print("Inserisci la tua email: ")
                    emailTest = readLine()!!.toString()

                    controlloSicurezza = false

                    var test: User = User() //inserisco i dati dell'utente in una variabile di appoggio
                    test.username = usernameTest
                    test.password = passwordTest
                    test.email = emailTest

                    while (controlloSicurezza == false) {   //inizio ciclo sicurezza dell'inserimento

                        print("Sei sicuro? Scegli tra YES o NO: ")
                        var sure: String = readLine()!!.toString()

                        if (sure.equals("YES", ignoreCase = true)) {

                            var controllo : Boolean = Controllo(test)
                            //richiamo la funzione che ha lo scopo di controllare che username ed email siano univoci

                            if (controllo == true){ //username o email gia' esistenti
                                controlloSicurezza = true
                            }
                            else{   //username e email non esistenti
                                utentiTotali.add(test)  //aggiungo i dati dell'utente nell'array degli utenti
                                appoggioUtenti = test   //aggiungo i dati dell'utente nella variabile di appoggio del controllo degli ID
                                println("Registrazione avvenuta con successo!")
                                controlloSicurezza = true
                                controlloScelta1 = true
                                controlloLogin = true
                            }

                        } else if (sure.equals("NO", ignoreCase = true)) {  //caso utente non sicuro dell'inserimento dei dati
                            controlloSicurezza = true
                        } else {
                            println("Devi scegliere tra YES o NO! Riprova") //caso di errore scelta opzione
                        }
                    }   //fine ciclo controlloSicurezza
                }   //fine ciclo controlloScelta1

            } else {
                println("Devi scegliere tra SI o NO! Riprova")  //caso di errore scelta opzione
            }

        }   //fine ciclo controlloLogin

        //RESET DELLE VARIABILI DI CONTROLLO DEI CICLI
        controlloLogin = false
        controlloDati = false
        controlloSicurezza = false
        controlloScelta1 = false
        controlloScelta = false

        //LISTA TASKS
        if (tasks.size == 0) {  //il primo utente fa un primo inserimento in caso non abbai gia' inserito una task
            print("Inserisci il nome della task: ")
            var aggiuntaNome = readLine()!!.toString()
            var statoDefault = false
            Create(aggiuntaNome, statoDefault, appoggioUtenti)
        } else {
            for (i in 0 until tasks.size) {
                if (appoggioUtenti.id.equals(tasks.get(i).id)) {    //caso in cui l'utente loggato ha gia' inserito una task
                    break
                } else {    //caso in cui l'utente loggato non ha inserito una task
                    print("Inserisci il nome della task: ")
                    var aggiuntaNome = readLine()!!.toString()
                    var statoDefault = false
                    Create(aggiuntaNome, statoDefault, appoggioUtenti)  //creazione della task
                    break
                }
            }
        }


        println("----------LISTA DELLE TASKS----------")
        StampaTasks(appoggioUtenti) //stampa tutte le task dell'utente loggato a schermo

        while (controlloMenuTask == false) {    //inizio ciclo controlloMenuTask

            print("Vuoi AGGIUNGERE - MODIFICARE - ELIMINARE una delle task o USCIRE? [AGGIUNGI][MODIFICA][ELIMINA][ESCI]: ")
            scelta2 = readLine()!!.toString()   //scelta dell'utente

            if (scelta2.equals("AGGIUNGI", ignoreCase = true)) {    //CASO AGGIUNGI

                print("Inserisci il nome della task: ")
                var aggiuntaNome = readLine()!!.toString()
                while (controlloAggiunta == false) {    //inizio ciclo controlloAggiunta

                    print("Vuoi mettere la task tra i preferiti? Scegli tra [SI][NO]: ")
                    var scelta = readLine()!!.toString()    //l'utente sceglie se inserire la task tra i preferiti o no

                    if (scelta.equals("SI", ignoreCase = true)) {   //caso inserimento tra i preferiti
                        var aggiuntaStato = true
                        Create(aggiuntaNome, aggiuntaStato, appoggioUtenti)
                        controlloAggiunta = true
                        StampaTasks(appoggioUtenti)
                    } else if (scelta.equals("NO", ignoreCase = true)) {    //caso inserimento non tra i preferiti
                        var aggiuntaStato = false
                        Create(aggiuntaNome, aggiuntaStato, appoggioUtenti)
                        controlloAggiunta = true
                        StampaTasks(appoggioUtenti)
                    } else {
                        println("Devi scegliere tra SI o NO! Riprova")  //messaggio errore di inserimento scelta
                    }

                }   //fine ciclo controlloAggiunta

            } else if (scelta2.equals("MODIFICA", ignoreCase = true)) { //CASO MODIFICA

                StampaTasks(appoggioUtenti)
                var riferimento: Int = 0
                while (controlloModifica == false) {    //inizio ciclo controlloModifica

                    print("Quale task vuoi modificare? Inserisci un numero da 1 a ${tasks.size}: ")
                    riferimento = readLine()!!.toInt()  //richiesta del riferimento della task da rimuovere all'utente

                    if (riferimento >= 1 && riferimento <= tasks.size) {    //controllo che il dato inserito dall'utente rispetti i criteri
                        controlloModifica = true
                    } else {
                        println("Devi inserire un numero compreso tra 1 e ${tasks.size}! Riprova")
                    }

                }   //fine ciclo controlloModifica
                riferimento = riferimento - 1
                print("Inserisci il nuovo nome della task: ")
                var nuovoNome = readLine()!!.toString() //inserimento del nuovo nome della task

                while (controlloAggiunta == false) {    //inizio ciclo controllo preferenza

                    print("Vuoi mettere la task tra i preferiti? Scegli tra [SI][NO]: ")
                    var scelta = readLine()!!.toString()    //chiedo all'utente se vuole cambaire la preferenza della task

                    if (scelta.equals("SI", ignoreCase = true)) {
                        var aggiuntaStato = true    //cambio preferenza
                        var appoggio: ToDo = ToDo() //variabile di appoggio
                        appoggio.name = nuovoNome
                        appoggio.status = aggiuntaStato
                        Update(riferimento, appoggio, appoggioUtenti)   //funzione di modifica
                        StampaTasks(appoggioUtenti) //stampo a schermo le modifiche fatte
                        controlloAggiunta = true
                    } else if (scelta.equals("NO", ignoreCase = true)) {
                        var aggiuntaStato = false   //non metto la task tra i preferiti
                        var appoggio: ToDo = ToDo() //variabile di appoggio
                        appoggio.name = nuovoNome
                        appoggio.status = aggiuntaStato
                        Update(riferimento, appoggio, appoggioUtenti)   //funzione di modifica
                        StampaTasks(appoggioUtenti) //stampo a schermo le modifiche fatte
                        controlloAggiunta = true
                    } else {
                        println("Devi scegliere tra SI o NO! Riprova")  //messaggio errore di inserimento scelta
                    }

                }   //fine ciclo controllo preferenza
                println("Modifica avvenuta con successo")

            } else if (scelta2.equals("ELIMINA", ignoreCase = true)) {  //CASO ELIMINA

                StampaTasks(appoggioUtenti) //stampo all'utente la lista delle task per far scegliere quale eliminare
                //print(tasks.size)
                var riferimento: Int = 0
                while (controlloElimina == false) { //inizio ciclo controlloElimina

                    print("Quale task vuoi eliminare? Inserisci un numero da 1 a ${tasks.size}: ")
                    riferimento = readLine()!!.toInt()  //l'utente inserisce il riferimento della task che vuole eliminare

                    if (riferimento >= 1 && riferimento <= tasks.size) {    //controllo che il dato inserito dall'utente rispetti i criteri
                        controlloElimina = true
                    } else {
                        println("Devi inserire un numero compreso tra 1 e ${tasks.size}! Riprova")  //messaggio errore
                    }

                }   //fine ciclo controlloElimina
                riferimento = riferimento - 1
                Delete(riferimento, appoggioUtenti) //funzione delete
                println("Eliminazione avvenuta con successo")
                StampaTasks(appoggioUtenti) //stampa delle modifiche a schermo

            } else if (scelta2.equals("ESCI", ignoreCase = true)) { //CASO ESCI

                print("Inserisci il nome che avra' il file .txt: ")
                //chiedo all'utente di inserire il nome del file.txt che verra' creato con la lista delle task
                var nomeTxt = readLine()!!.toString()
                StampaTxt(appoggioUtenti, nomeTxt)  //funzione stampa del file txt
                controlloMenuTask = true

            } else {
                println("Devi scegliere tra AGGIUNGI, MODIFICA o ELIMINA! Riprova") //messaggio errore scelta
            }

            //RESET DELLE VARIABILI DI CONTROLLO
            controlloAggiunta = false
            controlloModifica = false
            controlloElimina = false

        }   //fine ciclo controlloMenuTask

        controlloMenuTask = false

    }   //fine ciclo controlloApp

}

/*
    TEST LOGIN
    var test : User = User()
    test.username = "alessandro"
    test.password = "password"
    test.email = "alessandro@email.it"
    utentiTotali.add(test)

    for (i in 0 until utentiTotali.size){
        print("username: ")
        println(utentiTotali.get(i).username)
        print("email: ")
        println(utentiTotali.get(i).email)
        print("pass: ")
        println(utentiTotali.get(i).password)
    }

    TEST CRUD
    var nome = "Ciao"
    var stato = true

    var test : ToDo = ToDo()
    test.name = "filippo"
    test.status = false

    Create(nome, stato)
    StampaTasks()
    Update(0, test)
    var contenitore = Read(0)
    StampaSemplice(contenitore)
    Create(nome, stato)
    Delete(0)
    StampaTasks()

 */
