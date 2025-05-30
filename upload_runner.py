from app.upload import load_and_prepare_docs, upload_docs_to_mongo
import sys
if __name__ == "__main__":
    import sys
    if len(sys.argv) != 2:
        print("Sử dụng: python upload_runner.py <duong_dan_file_json>")
        exit(1)

    json_path = sys.argv[1]
    load_and_prepare_docs(json_path)
    upload_docs_to_mongo()
