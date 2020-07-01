package com.klaytn.caver.methods.response;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.web3j.protocol.ObjectMapperFactory;
import org.web3j.protocol.core.Response;

import java.io.IOException;


public class Account extends Response<Account.AccountData> {
    @JsonDeserialize(using = AccountData.AccountDeserializer.class)
    public static class AccountData {
        int accType;
        private AccountType account;

        public AccountData(int accType, AccountType account) {
            this.accType = accType;
            this.account = account;
        }

        public int getAccType() {
            return accType;
        }

        public void setAccType(int accType) {
            this.accType = accType;
        }

        public AccountType getAccount() {
            return account;
        }

        public void setAccount(AccountType account) {
            this.account = account;
        }


        public static class AccountDecoder {

            private static ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();

            public static AccountType decode(AccountType.Key keyType, JsonNode key) throws IOException {
                if (keyType == AccountType.Key.EOA) {
                    return objectMapper.readValue(key.toString(), AccountTypeEOA.class);
                }
                return objectMapper.readValue(key.toString(), AccountSmartContract.class);
            }
        }

        public static class AccountDeserializer extends JsonDeserializer<AccountData> {
            @Override
            public AccountData deserialize(
                    JsonParser jsonParser,
                    DeserializationContext deserializationContext) throws IOException {
                JsonNode node = jsonParser.getCodec().readTree(jsonParser);
                JsonNode key = node.get("account");
                AccountType.Key accType = AccountType.Key.getType(node.get("accType").intValue());
                return new AccountData(accType.getKeyType(), AccountDecoder.decode(accType, key));
            }
        }
    }
}