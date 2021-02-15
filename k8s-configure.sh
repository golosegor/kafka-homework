#!/bin/bash
helm upgrade eg-release-kafka       --install --wait -f k8s/kafka.yaml bitnami/kafka
helm upgrade eg-prometheus-operator --install --wait -f k8s/prometheus-operator.yaml prometheus-community/kube-prometheus-stack
helm upgrade eg-prometheus-adapter  --install --wait -f k8s/prometheus-adapter.yaml prometheus-community/prometheus-adapter