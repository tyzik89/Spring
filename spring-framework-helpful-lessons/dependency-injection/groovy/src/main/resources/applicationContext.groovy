import com.work.vladimirs.BaseRepository
import com.work.vladimirs.BaseService
import com.work.vladimirs.DependentService

beans {
    base BaseService
    repository BaseRepository

    dependent(DependentService, repository, base)
}