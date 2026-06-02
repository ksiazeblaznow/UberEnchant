## Fork of UberEnchant by coltonj96
[UberEnchant by coltonj96](https://github.com/coltonj96/UberEnchant)

### Why forking?

Because of performance — leaking caused even up to 800ms avg. time so ~1 TPS.

In the original repo tasks are stored in `ArrayList` and removed on specific conditions - those seemed to be failing in some edge cases.
Another leak was happening inside of `UberMeta` tags creation - resulting in easily 17k+ entries which were then searched within HashTree functions.
At that moment reloading server caused a relieve for ~20 mins, then it raised gradually easily up to 150ms and more.

### Change
- Replaced the underlying `ArrayList` tracking system with a `HashMap` (`PlayerUUID_SLOT`). Tasks are now uniquely mapped per equipment slot, automatically overwriting and discarding old tasks on insertion.
- Introduced check if `UberMeta` tag already exists. Reduced array size to ~12 from ever-growing number (17k+)

### Results
Performance is now at stable ~0.45 ms avg. on Raspberry Pi 5.
