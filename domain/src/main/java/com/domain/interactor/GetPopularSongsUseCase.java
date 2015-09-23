package com.domain.interactor;

import com.domain.Song;
import com.domain.executor.PostExecutionThread;
import com.domain.executor.ThreadExecutor;
import com.domain.repository.SongRepository;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

public class GetPopularSongsUseCase extends UseCase {

    private final SongRepository songRepository;

    @Inject
    public GetPopularSongsUseCase(SongRepository songRepository, ThreadExecutor threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.songRepository = songRepository;
    }

    @Override
    protected Observable<List<Song>> buildUseCaseObservable() {
        return songRepository.getPopularSongs();
    }

}
