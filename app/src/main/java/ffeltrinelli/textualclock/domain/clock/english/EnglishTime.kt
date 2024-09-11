package ffeltrinelli.textualclock.domain.clock.english

import ffeltrinelli.textualclock.domain.words.Word
import java.time.LocalTime

class EnglishTime {

    fun currentTime(): LocalTime = LocalTime.now()

    fun convertToWords(time: LocalTime): List<Word> {
        // TODO implement english time logic
        return emptyList()
    }
}
