package com.ingl0ri0us.cryptoassetstracker.data.entity.room.db;

import androidx.room.RoomDatabase;

import com.ingl0ri0us.cryptoassetstracker.data.entity.room.RoomShortCoinInfo;
import com.ingl0ri0us.cryptoassetstracker.data.entity.room.dao.DaoShortCoinInfo;

@androidx.room.Database(entities = RoomShortCoinInfo.class, version = 1)
public abstract class Database extends RoomDatabase {
    public static final String DB_NAME = "cryptoAssetsDatabase.db";

    public abstract DaoShortCoinInfo getDaoShortCoinInfo();
}