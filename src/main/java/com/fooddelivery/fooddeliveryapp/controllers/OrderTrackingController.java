package com.fooddelivery.fooddeliveryapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/order")
public class OrderTrackingController {

    @Autowired
    private SimpMessagingTemplate template;

    // Méthode pour suivre la commande et envoyer des mises à jour via WebSocket
    @MessageMapping("/trackOrder")
    public void trackOrder(@Payload Long orderId) {
        String status = getOrderStatus(orderId);  // Obtient le statut de la commande à partir de la base de données
        template.convertAndSend("/topic/orderStatus/" + orderId, status); // Envoie le statut à tous les abonnés
    }

    // Cette méthode simule l'obtention du statut de la commande
    private String getOrderStatus(Long orderId) {
        // Vous pouvez ajouter une logique pour récupérer le statut de la commande depuis votre base de données
        return "Commande " + orderId + " en cours de préparation.";
    }
}
