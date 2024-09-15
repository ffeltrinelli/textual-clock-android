package ffeltrinelli.textualclock

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.Clock
import kotlin.random.Random

@Module
@InstallIn(SingletonComponent::class)
object SingletonDependencies {
    @Provides
    fun random(): Random = Random

    @Provides
    fun clock(): Clock = Clock.systemDefaultZone()
}
