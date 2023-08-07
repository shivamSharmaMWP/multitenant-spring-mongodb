package com.multitenant;

import org.springframework.stereotype.Component;


public class TenantHolder {
    private static ThreadLocal<String> tenantId = new ThreadLocal<>();

    public static String getTenantId() {
        return tenantId.get();
    }

    public static void setTenantId(final String tenantId) {
        TenantHolder.tenantId.set(tenantId);
    }

    public static void clear() {
        tenantId.remove();
    }
}