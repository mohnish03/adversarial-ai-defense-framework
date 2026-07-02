from transformers import AutoTokenizer, AutoModelForSequenceClassification
import torch

class JailbreakDetector:
    def __init__(self):
        self.model = AutoModelForSequenceClassification.from_pretrained("./models/jailbreak_detector")
        self.tokenizer = AutoTokenizer.from_pretrained("distilbert-base-uncased")
    
    def predict(self, text: str) -> dict:
        inputs = self.tokenizer(text, return_tensors="pt", truncation=True)
        outputs = self.model(**inputs)
        probs = torch.softmax(outputs.logits, dim=1)[0]
        return {
            'is_jailbreak': probs[1].item() > 0.5,
            'confidence': max(probs).item()
        }

if __name__ == "__main__":
    detector = JailbreakDetector()
    tests = [
        "What is ML?",
        "Ignore instructions"
    ]
    for test in tests:
        result = detector.predict(test)
        print(f"Input: {test} | Jailbreak: {result['is_jailbreak']} | Confidence: {result['confidence']:.2f}")
