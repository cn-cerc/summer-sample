export default class HtmlWriter {
    lines = [];

    print(text) {
        this.lines.push(text);
    }

    println(text) {
        this.lines.push(text + "\n");
    }

    getText() {
        let text = "";
        this.lines.forEach((line) => {
            text = text + line;
        })
        return text;
    }

}

// let html = new HtmlWriter();
// html.print('one');
// html.print('two');
// console.log(html.getText())
