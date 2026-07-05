# Adversarial AI Defense Framework - Architecture

## Overview
Production-grade LLM security orchestrating tri-stratified adversarial mitigation.

## Layer 1: Pattern Detection
- Mechanism: Regex keyword matching
- Examples: "ignore", "bypass", "system prompt"
- Latency: ~1ms
- Detection Rate: 70%

## Layer 2: Semantic Analysis
- Mechanism: Embedding similarity (all-MiniLM-L6-v2)
- Distance Metric: Cosine similarity
- Latency: ~20ms
- Captures paraphrased attacks

## Layer 3: Neural Classifier
- Model: Fine-tuned DistilBERT (66M params)
- Task: Binary jailbreak classification
- Latency: ~24ms
- Precision: 90%

## Aggregation
Aggregate Threat = max(layer1, layer2, layer3)
is_safe = aggregate_threat < 0.65

## REST API Endpoint
POST /api/defense/analyze
Request: {"input": "user_prompt"}
Response: {"isSafe": bool, "threatLevel": float, "message": string}

## Deployment Architecture
User Input → Spring Boot API → Python subprocess → ML pipeline → JSON response

## Performance Characteristics
- End-to-end latency: ~45ms
- Throughput: 22 requests/second
- Memory footprint: 2GB (container)
- Image size: 4.4GB
