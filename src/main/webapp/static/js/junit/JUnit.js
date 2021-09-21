
export function assertEquals(one, two, three) {
    let flag = one;
    let src = two;
    let tar = three;
    switch (arguments.length) {
        case 2: {
            flag = '';
            src = one;
            tar = two;
            break;
        }
        case 3: {
            break;
        }
        default:
            console('junit error call');
            return false;
    }

    if (src == tar) {
        console.log(`junit ${flag}: yes`);
        return true;
    }

    console.log(`junit ${flag}: no:`);
    console.log('source=' + src);
    console.log('target=' + tar);
    return false;
}
