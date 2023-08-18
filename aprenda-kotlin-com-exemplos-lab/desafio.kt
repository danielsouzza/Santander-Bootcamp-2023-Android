// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Nivel { BASICO, INTERMEDIARIO, DIFICIL }

data class Usuario(val name:String)

data class ConteudoEducacional(val nome: String, val duracao: Int = 60, nivel: Nivel)

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {

    val inscritos = mutableListOf<Usuario>()
    
    fun matricular(usuario: Usuario) {
        inscritos += usuario
    }

    fun showConteudoEducacional(){
        inscritos.forEach{
            
        }
    }
}

fun main() {
    val bootcampKotlin = Formacao(
            "Bootcamp - kotlin",
            listOf(
                ConteudoEducacional("Introdução",30, Nivel.BASICO),
                ConteudoEducacional("variáveis",30, Nivel.BASICO),
                ConteudoEducacional("Condições",30, Nivel.BASICO)
            )
        )

    
}
