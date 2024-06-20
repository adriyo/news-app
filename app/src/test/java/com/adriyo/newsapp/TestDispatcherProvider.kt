import com.adriyo.newsapp.shared.util.CoroutineDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher

/**
 * Created by adriyo on 06/03/2024.
 * <a href="https://github.com/adriyo">Github</a>
 */
@ExperimentalCoroutinesApi
class TestDispatcherProvider : CoroutineDispatchers {

    private val testDispatcher = UnconfinedTestDispatcher()

    override val main: CoroutineDispatcher
        get() = testDispatcher
    override val io: CoroutineDispatcher
        get() = testDispatcher
}