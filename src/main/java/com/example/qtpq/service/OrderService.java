package com.example.qtpq.service;

import com.example.qtpq.dto.*;
import com.example.qtpq.enums.ResponseCode;
import com.example.qtpq.model.*;
import com.example.qtpq.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class OrderService {
    @Autowired
    private OrdersRepository orderRepository;

    private LocationRepository locationRepository;
    private SellerRepository sellerRepository;
    private ProductRepository productRepository;
    private OrderDetailRepository orderDetailRepository;
    private TransactionRepository transactionRepository;
    private WalletRepository walletRepository;

    public ResponseObject createOrder(CreateOrderDTO createOrderDTO) {
        ResponseObject responseObject = new ResponseObject();
        LocalDate date = LocalDate.now();
        Location location = locationRepository.findLocationByName(createOrderDTO.getLocationName());
        if (location == null) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData("Location cant be found");
        }
        log.info("Seller id {}", createOrderDTO.getSellerId());
        log.info("Seller data {}", sellerRepository.findById(createOrderDTO.getSellerId()).get().getName());
        Seller seller = sellerRepository.findById(createOrderDTO.getSellerId())
                .orElseThrow(() -> {
                    throw new IllegalStateException("Invalid seller");
                });
        try {
            Orders orders = new Orders();
            orders.setCustomerName(createOrderDTO.getCustomerName());
            orders.setRoomNumber(createOrderDTO.getRoomNumber());
            orders.setLocation(location);
            orders.setOrderDate(date);
            orders.setPhone(createOrderDTO.getPhone());
            orders.setSeller(seller);
            orders.setMenu(seller.getMenu());
            orders.setState("NEW");

            Orders savedOrder = orderRepository.save(orders);
            log.info("saved orders {}", savedOrder.getId());
            int length = createOrderDTO.getOrderDetailsDTOS().size();
            double totalPrice = 0;
            for (int i = 0; i < length; i++) {
                Long productId = createOrderDTO.getOrderDetailsDTOS().get(i).getProductId();
                Product product = productRepository.findById(productId)
                        .orElseThrow(() ->
                        {
                            throw new IllegalStateException("Product have id " + productId + " does not exist");
                        });
                int quanlity = createOrderDTO.getOrderDetailsDTOS().get(i).getQuanlity();
                if (product.getQuanlity() < quanlity) {
                    throw new IllegalStateException("Product have id " + productId + " does not enough to sell");
                }
                double price = quanlity * product.getUnitPrice();
                totalPrice += price;

                OrderDetail orderDetail = OrderDetail.builder()
                        .order(savedOrder)
                        .product(product)
                        .price(price)
                        .quality(quanlity)
                        .build();
                orderDetailRepository.save(orderDetail);
                product.setQuanlity(product.getQuanlity() - quanlity);
                productRepository.save(product);
            }
            double amount = totalPrice * 0.03;
            Transactions transactions = new Transactions(null, amount, date, savedOrder.getSeller().getWallet(), savedOrder);
            Transactions savedTrans = transactionRepository.save(transactions);
            Wallet wallet = savedOrder.getSeller().getWallet();
            wallet.setBalance(wallet.getBalance() + amount);

//            List<Transaction> transactionDTOList = new ArrayList<>();
//            List<Transaction> transactions = transactionRepository.findAllByWallet(wallet.getWalletId());
//
//            for (int i = 0; i < transactions.size(); i++) {
//                Transaction trans = new Transaction();
//                trans.setAmount(transactions.get(i).getAmount());
//                trans.setCreateDate(transactions.get(i).getCreateDate());
//                transactionDTOList.add(trans);
//            }
//            System.out.println("ta daaaaaaaaaa -------------------------" + transactions.size());

            walletRepository.save(wallet);


            savedOrder.setTotalPrice(totalPrice);
            savedOrder.setTransactions(savedTrans);
            orderRepository.save(savedOrder);

            responseObject.setData("Create done");
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }

        return responseObject;
    }

    public ResponseObject getListOrder() {
        ResponseObject responseObject = new ResponseObject();
        try {
            List<Orders> ordersList = orderRepository.findAll();
            List<ResponseOrderDTO> list = new ArrayList<>();
            for (Orders order : ordersList) {
                ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO(order);

                list.add(responseOrderDTO);

            }

            responseObject.setData(list);
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }

        return responseObject;
    }

    public ResponseObject getOrderById(Long id) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Orders orders = orderRepository.findById(id).orElseThrow(() -> {
                throw new IllegalStateException("This orders does not exist");
            });
            responseObject.setData(new ResponseOrderDTO(orders));
            responseObject.setStatus(ResponseCode.Common.SUCCESS.getCode());
            responseObject.setMessage(ResponseCode.Common.SUCCESS.getMessage());
        } catch (Exception e) {
            responseObject.setStatus(ResponseCode.Common.FAILED.getCode());
            responseObject.setMessage(ResponseCode.Common.FAILED.getMessage());
            responseObject.setData(e.getMessage());
        }

        return responseObject;
    }
}
