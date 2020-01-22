package com.ingl0ri0us.cryptoassetstracker.data.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ingl0ri0us.cryptoassetstracker.data.entity.room.RoomShortCoinInfo;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface DaoShortCoinInfo {
    @Insert(onConflict = REPLACE)
    void insert(RoomShortCoinInfo coin);

    @Update
    void update(RoomShortCoinInfo coin);

    @Delete
    void delete(RoomShortCoinInfo coin);

    //check this query, error possible
    @Query("SELECT * FROM RoomShortCoinInfo")
    List<RoomShortCoinInfo> getAll();

    @Query("SELECT * FROM RoomShortCoinInfo WHERE coinName = :coinName LIMIT 1")
    RoomShortCoinInfo findByCoinName(String coinName);

    @Query("SELECT * FROM RoomShortCoinInfo WHERE coinId = :coinId LIMIT 1")
    RoomShortCoinInfo findByCoinId(int coinId);
}