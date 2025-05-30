import requests

TOGETHER_API_KEY = "1ea49e30f8ca82bf0dcb17fe5e92a4ffe28f8f54d329a158f160a508992556bd"  # Thay bằng key của bạn

def ask_llama(prompt: str) -> str:
    headers = {"Authorization": f"Bearer {TOGETHER_API_KEY}"}
    body = {
        "model": "meta-llama/Llama-3.3-70B-Instruct-Turbo-Free",
        "messages": [{"role": "user", "content": prompt}],
        "temperature": 0.5,
        "max_tokens": 256
    }
    response = requests.post("https://api.together.xyz/v1/chat/completions", headers=headers, json=body)
    response_json = response.json()
    if "choices" in response_json:
        return response_json["choices"][0]["message"]["content"]
    else:
        return "❌ Lỗi khi gọi API LLM. Vui lòng thử lại."
