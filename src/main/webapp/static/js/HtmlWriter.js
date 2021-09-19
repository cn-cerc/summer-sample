export default class HtmlWriter {
    lines = [];

    print(text) {
        this.lines.push(text);
    }

    getText() {
        let text = "";
        this.lines.forEach((line) => {
            text = text + line + "\n";
        })
        return text;
    }

    writeHtml(htmlId) {
        window.document.body.innerHTML = this.getText();
    }

}

// let html = new HtmlWriter();
// html.print('one');
// html.print('two');
// console.log(html.getText())
