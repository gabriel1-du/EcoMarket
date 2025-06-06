package com.gateway.redireccionApis.Reporte;

public class ReportePulicRoutes {

    public static final String[] PEDIDO_PUBLIC_GET = {
        "/api/proxy/reportes",       // coincide con GET EXACTO a /api/proxy/medio-pago
        "/api/proxy/reportes/**"
    };
}
