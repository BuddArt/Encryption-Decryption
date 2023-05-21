package encryptdecrypt
import java.io.File

fun main(args: Array<String>) {
    if ("-data" in args) {
        val mode = args[args.indexOf("-mode") + 1]
        val key = args[args.indexOf("-key") + 1]
        val data = args[args.indexOf("-data") + 1]
        val alg = args[args.indexOf("-alg") + 1]
        modeData(mode, key, data, alg)
    } else if ("-in" in args) {
        val mode = args[args.indexOf("-mode") + 1]
        val key = args[args.indexOf("-key") + 1]
        val input = args[args.indexOf("-in") + 1]
        val output = args[args.indexOf("-out") + 1]
        val alg = args[args.indexOf("-alg") + 1]
        modeFile(mode, key, input, output, alg)
    } else println("Error")
}

fun modeData(md: String = "enc", k: String = "0", data: String = "", alg: String = "shift") {
    val key1 = k.toInt()
    var output = ""
    if (alg == "unicode") {
        when (md) {
            "enc" -> for (c in data) {
                print(c + key1)
            }

            "dec" -> for (c in data) {
                print(c - key1)
            }
        }
    } else if (alg == "shift") {
        when (md) {
            "enc" -> {
                for (c in data) {
                    output += if (c in 'a'..'z') {
                        if ((c + key1) > 'z') {
                            'a' + (key1 - ('z' - c) - 1)
                        } else c + key1
                    } else if (c in 'A'..'Z') {
                        if ((c + key1) > 'Z') {
                            'A' + (key1 - ('Z' - c) - 1)
                        } else c + key1
                    } else c
                }
            }
            "dec" -> {
                for (c in data) {
                    output += if (c in 'a'..'z') {
                        if ((c - key1) < 'a') {
                            'z' - (key1 - (c - 'a') - 1)
                        } else c - key1
                    } else if (c in 'A'..'Z') {
                        if ((c - key1) < 'A') {
                            'Z' - (key1 - (c - 'A') - 1)
                        } else c - key1
                    } else c
                }
            }
        }
    }
}

fun modeFile(md: String = "enc", k: String = "0", inp: String = "", otp: String = "", alg: String = "shift") {
    val key1 = k.toInt()
    var outputText = ""
    val separator = File.separator
    val path = System.getProperty("user.dir")
    val readFile = File("$path$separator$inp").readText()
    if (alg == "unicode") {
        when (md) {
            "enc" -> for (c in readFile) {
                outputText += (c + key1)
            }

            "dec" -> for (c in readFile) {
                outputText += (c - key1)
            }
        }
    } else if (alg == "shift") {
        when (md) {
            "enc" -> {
                for (c in readFile) {
                    outputText += if (c in 'a'..'z') {
                        if ((c + key1) > 'z') {
                            'a' + (key1 - ('z' - c) - 1)
                        } else c + key1
                    } else if (c in 'A'..'Z') {
                        if ((c + key1) > 'Z') {
                            'A' + (key1 - ('Z' - c) - 1)
                        } else c + key1
                    } else c
                }
            }
            "dec" -> {
                for (c in readFile) {
                    outputText += if (c in 'a'..'z') {
                        if ((c - key1) < 'a') {
                            'z' - (key1 - (c - 'a') - 1)
                        } else c - key1
                    } else if (c in 'A'..'Z') {
                        if ((c - key1) < 'A') {
                            'Z' - (key1 - (c - 'A') - 1)
                        } else c - key1
                    } else c
                }
            }
        }
    }
    File("$path$separator$otp").writeText(outputText)
}