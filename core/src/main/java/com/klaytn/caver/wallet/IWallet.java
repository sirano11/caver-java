package com.klaytn.caver.wallet;

import com.klaytn.caver.transaction.AbstractFeeDelegatedTransaction;
import com.klaytn.caver.transaction.AbstractTransaction;

import java.util.function.Function;

public interface IWallet<K> {

    K add(K keyring) throws Exception;

    boolean remove(String address) throws Exception;

    K getKeyring(String address);

    AbstractTransaction sign(String address, AbstractTransaction transaction) throws Exception;

    AbstractTransaction sign(String address, AbstractTransaction transaction, Function<AbstractTransaction, String> hasher) throws Exception;

    AbstractTransaction sign(String address, AbstractTransaction transaction, int index) throws Exception;

    AbstractTransaction sign(String address, AbstractTransaction transaction, int index, Function<AbstractTransaction, String> hasher) throws Exception;

    AbstractFeeDelegatedTransaction signAsFeePayer(String address, AbstractFeeDelegatedTransaction transaction) throws Exception;

    AbstractFeeDelegatedTransaction signAsFeePayer(String address, AbstractFeeDelegatedTransaction transaction, Function<AbstractFeeDelegatedTransaction, String> hasher) throws Exception;

    AbstractFeeDelegatedTransaction signAsFeePayer(String address, AbstractFeeDelegatedTransaction transaction, int index) throws Exception;

    AbstractFeeDelegatedTransaction signAsFeePayer(String address, AbstractFeeDelegatedTransaction transaction, int index, Function<AbstractFeeDelegatedTransaction, String> hasher) throws Exception;
}
