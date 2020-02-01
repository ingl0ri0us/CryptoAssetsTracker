package com.ingl0ri0us.cryptoassetstracker.di;

import androidx.room.Room;

import com.ingl0ri0us.cryptoassetstracker.App;
import com.ingl0ri0us.cryptoassetstracker.data.cache.Cache;
import com.ingl0ri0us.cryptoassetstracker.data.cache.RoomCache;
import com.ingl0ri0us.cryptoassetstracker.data.entity.room.db.Database;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class CacheModule {
    @Singleton
    @Provides
    Database roomDatabase(App app) {
        return Room
                .databaseBuilder(app, Database.class, Database.DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    Cache roomCache(Database database) {
        return new RoomCache(database);
    }
}