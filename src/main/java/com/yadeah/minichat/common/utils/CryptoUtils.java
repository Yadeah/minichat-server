package com.yadeah.minichat.common.utils;

import com.yadeah.minichat.common.utils.log.LogUtils;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 加解密工具类
 */
public class CryptoUtils {

    // region SHA
    public static String SHAEncryption(String password) {
        return SHAEncryption(password, "SHA");
    }

    private static String SHAEncryption(String password, String algorithm) {
        if (StringUtils.isEmpty(password)) {
            return null;
        }

        if (StringUtils.isEmpty(algorithm)) {
            // 默认算法为SHA
            algorithm = "SHA";
        }

        try {
            MessageDigest sha = MessageDigest.getInstance(algorithm);
            sha.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = sha.digest();
            return hex(bytes);
        } catch (Exception e) {
            LogUtils.error(e, "加密失败");
        }

        return password;
    }

    // 返回十六进制字符串
    private static String hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }
    // endregion

    // region AES

    /***
     * 生成加密秘钥
     */
    private static final String AES_SECRET_KEY = "babbf7612eae973b84d849f7b8466f59";
    private static SecretKeySpec getAESSecretKey() {
        //返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance("AES");

            //AES 要求密钥长度为 128
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG") ;
            secureRandom.setSeed(AES_SECRET_KEY.getBytes(StandardCharsets.UTF_8));
            kg.init(128, secureRandom);

            //生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), "AES");// 转换为AES专用密钥

        } catch (Exception e) {
            LogUtils.error(e, "AES生成加密密钥异常");
        }

        return null;
    }
    /***
     * AES加密
     * @param content 明文
     * @return 密文，异常时返回null
     */
    public static String AESEncrypt(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }

        try {
            // 创建密码器
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 初始化为加密模式的密码器
            cipher.init(Cipher.ENCRYPT_MODE, getAESSecretKey());

            // 加密
            byte[] result = cipher.doFinal(content.getBytes(StandardCharsets.UTF_8));

            //通过Base64转码返回
            return new String (Base64Utils.encode(result));
        } catch (Exception e) {
            LogUtils.error(e, "AES加密异常，明文={}", content);
        }

        return null;
    }

    /***
     * AES解密
     * @param content 密文
     * @return 明文，异常时返回null
     */
    public static String AESDecrypt(String content) {
        if (StringUtils.isEmpty(content) ||
                content.getBytes().length % 4 != 0 ||
                Base64.getDecoder().decode(content).length % 16 != 0) {
            return null;
        }

        try {
            //实例化
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");

            //使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getAESSecretKey());

            //执行操作
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(content));

            return new String(result, StandardCharsets.UTF_8);

        } catch (Exception e) {
            LogUtils.error(e, "AES解密异常，密文={}", content);
        }

        return null;
    }
    // endregion

}
