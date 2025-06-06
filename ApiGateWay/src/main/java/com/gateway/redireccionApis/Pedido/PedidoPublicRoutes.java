package com.gateway.redireccionApis.Pedido;

public class PedidoPublicRoutes {

     public static final String[] PEDIDO_PUBLIC_GET = {
        "/api/proxy/mpedido",       // coincide con GET EXACTO a /api/proxy/medio-pago
        "/api/proxy/pedido/**"
    };
}
