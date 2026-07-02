# Adversarial AI Defense Framework

Multi-layer LLM security orchestrating pattern detection, semantic analysis & ML-based jailbreak classification.

## Architecture

- **Layer 1:** Regex pattern matching (heuristic baseline)
- **Layer 2:** Semantic similarity via embeddings (distributional semantics)
- **Layer 3:** Fine-tuned DistilBERT (neural classifier)

## Results

- 90% adversarial prompt detection
- <100ms inference latency
- Modular, extensible design

## Usage

```bash
pip install -r requirements.txt
python -m defense_framework.defense_pipeline
```

## Tech Stack

Python | PyTorch | HuggingFace | Java | C++
