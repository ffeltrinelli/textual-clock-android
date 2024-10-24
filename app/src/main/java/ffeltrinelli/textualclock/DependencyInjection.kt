package ffeltrinelli.textualclock

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ffeltrinelli.textualclock.data.PreferencesHelper
import ffeltrinelli.textualclock.data.SharedPreferencesHelper
import ffeltrinelli.textualclock.domain.clock.fill.ClockRowFiller
import ffeltrinelli.textualclock.domain.clock.fill.RandomRowFiller
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

@Module
@InstallIn(SingletonComponent::class)
abstract class Bindings {
    @Binds
    abstract fun clockRowFiller(randomRowFiller: RandomRowFiller): ClockRowFiller

    @Binds
    abstract fun preferenceHelper(sharedPreferencesHelper: SharedPreferencesHelper): PreferencesHelper
}
