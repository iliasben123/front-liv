package com.fooddelivery.fooddeliveryapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class OrderStatusUpdater {

    @Autowired
    private SimpMessagingTemplate template;

    // Simule l'envoi d'une mise à jour de statut toutes les 10 secondes
    @Scheduled(fixedRate = 10000)
    public void sendPeriodicUpdate() {
        Long orderId = 123L;  // L'ID de la commande à mettre à jour
        String status = "Commande " + orderId + " en cours de livraison.";
        template.convertAndSend("/topic/orderStatus/" + orderId, status);
    }
}
