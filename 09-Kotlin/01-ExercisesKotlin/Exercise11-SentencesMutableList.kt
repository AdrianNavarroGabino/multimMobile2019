// Adrián Navarro Gabino

/*
 * Sentences (MutableList)
 */

fun main() {
    
    val sentences: MutableList<String?> = mutableListOf()
    
    do
    {
        println("Enter a sentence (fin to exit)")
        var sentence: String? = readLine()
        
        if(sentence != "fin")
        {
            sentences.add(sentence)
        }
    } while(sentence != "fin")
    
    for(i in sentences.size - 1 downTo 0)
    {
        println(sentences[i])
    }
}